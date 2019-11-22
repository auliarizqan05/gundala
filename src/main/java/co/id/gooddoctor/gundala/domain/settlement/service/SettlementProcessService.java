package co.id.gooddoctor.gundala.domain.settlement.service;

import co.id.gooddoctor.gundala.domain.settlement.dao.MerchantDao;
import co.id.gooddoctor.gundala.domain.settlement.dao.SettlementFileDao;
import co.id.gooddoctor.gundala.domain.settlement.entity.Merchant;
import co.id.gooddoctor.gundala.domain.settlement.entity.SettlementFile;
import co.id.gooddoctor.gundala.domain.settlement.model.SettlementItemModel;
import co.id.gooddoctor.gundala.domain.settlement.model.SettlementModel;
import co.id.gooddoctor.gundala.infrastructure.util.ConstantUtil;
import co.id.gooddoctor.gundala.infrastructure.util.ExcelUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
public class SettlementProcessService {

    private static Logger logger = LoggerFactory.getLogger(SettlementProcessService.class);
    private final static DateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

    @Value("${file.generate.path}")
    private String filePath;

    @Autowired
    MerchantDao merchantDao;

    @Autowired
    SettlementFileDao settlementFileDao;

    public String uploadData(MultipartFile file, Date dateUpload) {

        if (!StringUtils.equalsAnyIgnoreCase(
                file.getContentType(),
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
                "application/vnd.ms-excel")) {

            throw new IllegalArgumentException("File format not supported");
        }
        String result;
        try {
            //extract data from excel
            List<SettlementItemModel> settlementItemModelList = extractDataExcel(file);

            //grouping data by vendor id
            Map<BigDecimal, List<SettlementItemModel>> groupByVendorId = mappingByVendorId(settlementItemModelList,
                    dateUpload);

            //process generate report for settlement
            processGenerateSettlement(groupByVendorId, dateUpload);

            result = "Upload Success";
        } catch (Exception e) {
            logger.error("Failed to generate report : {} ", e.getMessage());
            throw new IllegalArgumentException(e);
        }

        return result;
    }

    private List<SettlementItemModel> extractDataExcel(MultipartFile file) {

        List<SettlementItemModel> settlementItemModelList = new ArrayList<>();
        try (InputStream excelFile = file.getInputStream();
             XSSFWorkbook workbook = new XSSFWorkbook(excelFile)) {

            XSSFSheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();

            rowIterator.forEachRemaining(row -> {

                BigDecimal vendorId = BigDecimal.valueOf((double) ExcelUtil.cellValue(row.getCell(1)));
                String status = (String) ExcelUtil.cellValue(row.getCell(6));
                Date orderCreatedDate = row.getCell(7) == null ?
                        null : (Date) ExcelUtil.cellValue(row.getCell(7));
                Date paymentDate = row.getCell(8) == null ?
                        null : (Date) ExcelUtil.cellValue(row.getCell(8));
                BigDecimal orderId = BigDecimal.valueOf((double) ExcelUtil.cellValue(row.getCell(0)));
                BigDecimal storeId = BigDecimal.valueOf((double) ExcelUtil.cellValue(row.getCell(3)));
                BigDecimal userId = BigDecimal.valueOf((double) ExcelUtil.cellValue(row.getCell(5)));

                String storeName = row.getCell(4) == null ?
                        "" : (String) ExcelUtil.cellValue(row.getCell(4));
                String vendorName = row.getCell(2) == null ?
                        "" : (String) ExcelUtil.cellValue(row.getCell(2));
                BigDecimal orderSubtotal = BigDecimal.valueOf((double) ExcelUtil.cellValue(row.getCell(14)));

                logger.info("the status for vendorId : {} is {} at {}",
                        NumberToTextConverter.toText(vendorId.doubleValue()),
                        status, orderCreatedDate);

                SettlementItemModel settlementItemModel = SettlementItemModel.builder().
                        orderId(orderId).
                        storeId(storeId).
                        userId(userId).
                        vendorId(vendorId).
                        orderCreatedDate(orderCreatedDate).
                        paymentDate(paymentDate).
                        paymentStatus(status).
                        storeName(storeName).
                        vendorName(vendorName).
                        orderSubtotal(orderSubtotal).
                        build();

                settlementItemModelList.add(settlementItemModel);

            });
        } catch (Exception e) {
            logger.error("Failed extract data from excel ", e);
            if (e instanceof ClassCastException) {
                throw new IllegalArgumentException("There something wrong with format or content data");
            } else if (e instanceof NullPointerException) {
                throw new IllegalArgumentException("The data is wrong, please check again");
            } else {
                throw new IllegalArgumentException(e);
            }

        }

        return settlementItemModelList;
    }

    private Map<BigDecimal, List<SettlementItemModel>> mappingByVendorId(List<SettlementItemModel>
                                                                                 settlementItemModelList,
                                                                         Date dateUpload) {

        //validation vendorId is exist or no
        List<Merchant> merchants = merchantDao.findAll();
        boolean isMerchantExist = merchants
                .stream()
                .allMatch(merchant ->
                        settlementItemModelList
                                .stream()
                                .anyMatch(settlementItemModel ->
                                        settlementItemModel.getVendorId().longValue() == merchant.getVendorId()));

        if (!isMerchantExist) {
            throw new IllegalStateException("Vendor ID isn't stored in the database yet");
        }

        return settlementItemModelList
                .stream()
                .filter(settlementItemModel ->
                        StringUtils.equalsAnyIgnoreCase(settlementItemModel.getPaymentStatus(),
                                ConstantUtil.STATUS_PAID_DELIVERED))
                .filter(settlementItemModel ->
                        DateUtils.isSameDay(dateUpload, settlementItemModel.getOrderCreatedDate()))
                .collect(Collectors.
                        groupingBy(SettlementItemModel::getVendorId,
                                Collectors.toList()));
    }

    private boolean processGenerateSettlement(Map<BigDecimal, List<SettlementItemModel>> groupByVendorId,
                                              Date dateUpload) {

        GenerateSettlement generateSettlement = new GenerateSettlement();
        AtomicBoolean statusProcess = new AtomicBoolean(false);
        try {

            logger.info("number of vendor id will generate : {} ", groupByVendorId.size());
            if (groupByVendorId.size() == 0) {
                logger.info("there is no data to generate report, might be date or status not satisfied");
                throw new IllegalStateException("There is no data to generate report");
            }
            groupByVendorId.entrySet().forEach(mapperByVendorId -> {

                int totalPendapatan = mapperByVendorId.getValue()
                        .stream()
                        .mapToInt(value -> value.getOrderSubtotal().intValue()).sum();
                long vendorId = mapperByVendorId.getKey().longValue();

                Merchant merchant = merchantDao.findByVendorId(vendorId).orElse(null);
                if (merchant == null) {
                    logger.info("vendor id {} not found in our database ", vendorId);
                } else {
                    SettlementModel settlementModel = SettlementModel
                            .builder()
                            .bank(merchant.getBankName())
                            .vendorName(merchant.getVendorName())
                            .vendorId(merchant.getVendorId())
                            .noRek(String.valueOf(merchant.getAccountNumber()))
                            .title(ConstantUtil.TITLE_GENERATE_REPORT)
                            .totalPendapatan(new BigDecimal(totalPendapatan))
                            .items(mapperByVendorId.getValue())
                            .build();

                    SettlementFile settlementFile = generateSettlement.generateExcel(settlementModel,
                            filePath, dateUpload);
                    statusProcess.set(saveSettlementFile(settlementFile));

                    logger.info("success generate report for Vendor Id : {}", merchant.getVendorId());
                }
            });

        } catch (Exception e) {
            logger.error("Error process generate report : {} ", e.getMessage());
            throw new IllegalArgumentException(e.getMessage());
        }

        return statusProcess.get();
    }

    private boolean saveSettlementFile(SettlementFile settlementFile) {

        boolean status = false;
        if (settlementFile != null) {
            SettlementFile settlementFileExist = settlementFileDao.findByVendorIdAndFileName(
                    settlementFile.getVendorId(),
                    settlementFile.getFileName());

            if (settlementFileExist == null) {
                settlementFileDao.save(settlementFile);
            } else {
                settlementFileExist.setFileDate(settlementFile.getFileDate());
                settlementFileDao.save(settlementFileExist);
            }

            logger.info("success save file {} to settlement file table", settlementFile.getFileName());

            status = true;
        }
        return status;
    }

}
