package co.id.gooddoctor.gundala.domain.settlement.service;

import co.id.gooddoctor.gundala.domain.settlement.constant.ColumnNameConstant;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
public class SettlementProcessService {

    private static Logger logger = LoggerFactory.getLogger(SettlementProcessService.class);
    private final static DateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy HH:mm");
    private final static DateFormat DATE_FORMAT_REPORT = new SimpleDateFormat("MM/dd/yyyy");

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
            Map<String, List<SettlementItemModel>> groupByVendorName = mappingByVendorName(settlementItemModelList,
                    dateUpload);

            //process generate report for settlement
            processGenerateSettlement(groupByVendorName, dateUpload);

            result = "Upload Success";
        } catch (Exception e) {
            logger.error("Failed to generate report : {} ", e.getMessage());
            throw new IllegalArgumentException(e);
        }

        return result;
    }

    private List<SettlementItemModel> extractDataExcel(MultipartFile file) {

        logger.info("running process extract data from file {} ", file.getName());
        List<SettlementItemModel> settlementItemModelList = new ArrayList<>();
        try (InputStream excelFile = file.getInputStream();

             XSSFWorkbook workbook = new XSSFWorkbook(excelFile)) {
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            sheet.getRow(0).getCell(1).getRichStringCellValue();

            Map<String, Integer> columnIndex = new HashMap<>();
            sheet.getRow(0).cellIterator().forEachRemaining(cell -> {
                String columnName = cell.getRichStringCellValue().getString();
                boolean isUsedColumn = ColumnNameConstant.COLUMN_NAME_INDEX(columnName);
                if (isUsedColumn)
                    columnIndex.put(columnName, cell.getColumnIndex());
            });

            rowIterator.next();
            rowIterator.forEachRemaining(row -> {
                BigDecimal vendorId = BigDecimal.valueOf(((double) ExcelUtil.cellValue(row.getCell(
                        columnIndex.get(ColumnNameConstant.VENDOR_ID)))));
                String status = (String) ExcelUtil.cellValue(row.getCell(
                        columnIndex.get(ColumnNameConstant.PAYMENT_STATUS)));

                Date lastStatusDate = null;
                try {
                    Date orderCreatedDate = (row.getCell(columnIndex.get(ColumnNameConstant.ORDER_CREATED_DATE)) == null) ?
                            null : DATE_FORMAT.parse((String) ExcelUtil.cellValue(
                            row.getCell(columnIndex.get(ColumnNameConstant.ORDER_CREATED_DATE))));
                    Date orderDeliveredDate = (row.getCell(columnIndex.get(ColumnNameConstant.ORDER_DELIVERED_DATE)) == null) ?
                            null : DATE_FORMAT.parse((String) ExcelUtil.cellValue(
                            row.getCell(columnIndex.get(ColumnNameConstant.ORDER_DELIVERED_DATE))));
                    lastStatusDate = orderDeliveredDate == null ? orderCreatedDate : orderDeliveredDate;
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                BigDecimal orderId = BigDecimal.valueOf((double) ExcelUtil.cellValue(row.getCell(
                        columnIndex.get(ColumnNameConstant.ORDER_ID))));
                BigDecimal storeId = BigDecimal.valueOf((double) ExcelUtil.cellValue(row.getCell(
                        columnIndex.get(ColumnNameConstant.STORE_ID))));
                BigDecimal userId = BigDecimal.valueOf(((double) ExcelUtil.cellValue(row.getCell(
                        columnIndex.get(ColumnNameConstant.USER_ID)))));
                String storeName = (row.getCell(columnIndex.get(ColumnNameConstant.STORE_NAME)) == null)
                        ? "" : (String) ExcelUtil.cellValue(row.getCell(columnIndex.get(ColumnNameConstant.STORE_NAME)));
                String vendorName = (row.getCell(columnIndex.get(ColumnNameConstant.VENDOR_NAME)) == null) ?
                        "" : (String) ExcelUtil.cellValue(row.getCell(
                        columnIndex.get(ColumnNameConstant.VENDOR_NAME)));

                Object total = ExcelUtil.cellValue(row.getCell(
                        columnIndex.get(ColumnNameConstant.ORDER_SUBTOTAL)));
                BigDecimal orderSubtotal = BigDecimal.valueOf((double) total);

                logger.debug("the status for vendorId : {} is {} at {}",
                        NumberToTextConverter.toText(vendorId.doubleValue()),
                        status, lastStatusDate);

                SettlementItemModel settlementItemModel = SettlementItemModel.builder().
                        orderId(orderId).
                        storeId(storeId).
                        userId(userId).
                        vendorId(vendorId).
                        lastStatusDate(lastStatusDate).
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

    private Map<String, List<SettlementItemModel>> mappingByVendorName(List<SettlementItemModel>
                                                                                 settlementItemModelList,
                                                                       Date dateUpload) {

        //validation vendorName is exist or no
        List<Merchant> merchants = merchantDao.findAll();
//        boolean isMerchantExist = merchants
//                .stream()
//                .allMatch(merchant ->
//                        settlementItemModelList
//                                .stream()
//                                .anyMatch(settlementItemModel ->
//                                        settlementItemModel.getVendorName().longValue() == merchant.getVendorName()));
//
//        if (!isMerchantExist) {
//            throw new IllegalStateException("Vendor Name isn't stored in the database yet");
//        }

        return settlementItemModelList
                .stream()
                .filter(settlementItemModel ->
                        StringUtils.equalsAnyIgnoreCase(settlementItemModel.getPaymentStatus(),
                                ConstantUtil.STATUS_PAID_DELIVERED))
                .filter(settlementItemModel ->
                        DateUtils.isSameDay(dateUpload, settlementItemModel.getLastStatusDate()))
                .filter(settlementItemModel -> merchants.stream().anyMatch(merchant ->
                        StringUtils.equals(settlementItemModel.getVendorName(), merchant.getVendorName())))
                .collect(Collectors.
                        groupingBy(SettlementItemModel::getVendorName,
                                Collectors.toList()));
    }

    private boolean processGenerateSettlement(Map<String, List<SettlementItemModel>> groupByVendorName,
                                              Date dateUpload) {

        GenerateSettlement generateSettlement = new GenerateSettlement();
        AtomicBoolean statusProcess = new AtomicBoolean(false);
        try {

            logger.info("number of vendor id will generate : {} ", groupByVendorName.size());
            logger.info("process generate file report ");
            if (groupByVendorName.size() == 0) {
                logger.info("there is no data to generate report, might be date or status not satisfied");
                throw new IllegalStateException("There is no data to generate report");
            }
            groupByVendorName.entrySet().forEach(mapperByVendorName -> {

                String vendorName = mapperByVendorName.getKey();

                Merchant merchant = merchantDao.findByVendorName(vendorName).orElse(null);
                if (merchant == null) {
                    logger.info("vendor name {} not found in our database ", vendorName);
                } else {

                    //calculate orderSubtotal
                    int totalOrderSubtotal = mapperByVendorName.getValue()
                            .stream()
                            .mapToInt(value -> value.getOrderSubtotal().intValue()).sum();

                    //set & calculate commission & settlementToMerchant
                    mapperByVendorName.getValue().forEach(settlementItemModel -> {
                        double commissions = (settlementItemModel.getOrderSubtotal().intValue()
                                * merchant.getCommissionPercentage()) / 100;
                        settlementItemModel.setCommission(commissions);

                        BigDecimal settlementToMerchant = settlementItemModel.getOrderSubtotal()
                                .subtract(BigDecimal.valueOf(commissions));

                        settlementItemModel.setSettlementToMerchant(settlementToMerchant);
                    });

                    //calculate commission
                    double totalCommission = mapperByVendorName.getValue()
                            .stream()
                            .mapToDouble(value -> value.getCommission()).sum();

                    //calculate settlementToMerchant
                    int total = mapperByVendorName.getValue()
                            .stream()
                            .mapToInt(value -> value.getSettlementToMerchant().intValue()).sum();

                    SettlementModel settlementModel = SettlementModel
                            .builder()
                            .bank(merchant.getBankName())
                            .vendorName(merchant.getVendorName())
                            .vendorId(merchant.getVendorId())
                            .noRek(String.valueOf(merchant.getAccountNumber()))
                            .companyName(merchant.getCompanyName())
                            .title(ConstantUtil.TITLE_GENERATE_REPORT)
                            .totalCommission(BigDecimal.valueOf(totalCommission))
                            .totalOrderSubtotal(new BigDecimal(totalOrderSubtotal))
                            .total(BigDecimal.valueOf(total))
                            .periodTrf(DATE_FORMAT_REPORT.format(dateUpload))
                            .items(mapperByVendorName.getValue())
                            .build();

                    SettlementFile settlementFile = generateSettlement.generateExcel(settlementModel,
                            filePath, dateUpload);
                    statusProcess.set(saveSettlementFile(settlementFile));

                    logger.debug("success generate report for Vendor Id : {}", merchant.getVendorName());
                }
            });

        } catch (Exception e) {
            logger.error("Error process generate report :  ", e);
            throw new IllegalArgumentException(e.getMessage());
        }
        logger.info("success create report {} ", DATE_FORMAT_REPORT.format(dateUpload));
        return statusProcess.get();
    }

    private boolean saveSettlementFile(SettlementFile settlementFile) {

        boolean status = false;
        if (settlementFile != null) {
            SettlementFile settlementFileExist = settlementFileDao.findByFileName(
                    settlementFile.getFileName());

            if (settlementFileExist == null) {
                settlementFileDao.save(settlementFile);
            } else {
                settlementFileExist.setFileDate(settlementFile.getFileDate());
                settlementFileDao.save(settlementFileExist);
            }

            logger.debug("success save file {} to settlement file table", settlementFile.getFileName());

            status = true;
        }
        return status;
    }

}
