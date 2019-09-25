package co.id.gooddoctor.gundala.domain.settlement.service;

import co.id.gooddoctor.gundala.domain.settlement.entity.Merchant;
import co.id.gooddoctor.gundala.domain.settlement.model.SettlementItemModel;
import co.id.gooddoctor.gundala.domain.settlement.model.SettlementModel;
import co.id.gooddoctor.gundala.infrastructure.util.ConstantUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SettlementProcess {

    private static Logger logger = LoggerFactory.getLogger(SettlementProcess.class);
    private static final String FILE_NAME = "C:\\Users\\Aulia Rizqan\\Documents\\document gdti\\" +
            "order_data (version 1).xlsb.xlsx";
    private static final String TITLE_GENERATE_REPORT = "Laporan Pendapatan Harian";
    private final static DateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

    @Autowired
    MerchantService merchantService;

    public String extractData(MultipartFile file, Date dateUpload) {

        String result;
        List<SettlementItemModel> settlementItemModelList = new ArrayList<>();
        try {
            //extract data from excel
            extractDataExcel(settlementItemModelList, file);

            //grouping data by vendor id
            Map<BigDecimal, List<SettlementItemModel>> groupByVendorId = mappingByVendorId(settlementItemModelList,
                    dateUpload);

            //process generate report for settlement
            processGenerateSettlement(groupByVendorId);

            result = "Success generate report";
        } catch (Exception e) {
            logger.error("Failed to generate report ", e);
            result = "Failed generate report, cause : " + e.getMessage();
        }

        return result;
    }

    private void extractDataExcel(List<SettlementItemModel> settlementItemModelList, MultipartFile file) {
        try (InputStream excelFile = file.getInputStream();
             XSSFWorkbook workbook = new XSSFWorkbook(excelFile)) {

            XSSFSheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();

            rowIterator.forEachRemaining(row -> {
                SettlementItemModel settlementItemModel;

                BigDecimal vendorId = BigDecimal.valueOf((double) cellValue(row.getCell(33)));
                String status = (String) cellValue(row.getCell(27));
                Date orderCreatedDate = (Date) cellValue(row.getCell(21));
                logger.info("the status for vendorId : {} is {} at {}",
                        NumberToTextConverter.toText(vendorId.doubleValue()),
                        status, orderCreatedDate);

                settlementItemModel = SettlementItemModel.builder().
                        orderId(BigDecimal.valueOf((double) cellValue(row.getCell(18)))).
                        storeId(BigDecimal.valueOf((double) cellValue(row.getCell(28)))).
                        userId(BigDecimal.valueOf((double) cellValue(row.getCell(32)))).
                        vendorId(vendorId).
                        orderCreatedDate(orderCreatedDate).
                        paymentDate((Date) cellValue(row.getCell(26))).
                        paymentStatus(status).
                        storeName((String) cellValue(row.getCell(29))).
                        vendorName((String) cellValue(row.getCell(34))).
                        orderSubtotal(BigDecimal.valueOf((double) cellValue(row.getCell(25)))).
                        build();

                settlementItemModelList.add(settlementItemModel);

            });
        } catch (Exception e) {
            logger.error("Failed extract data from excel ", e);
        }
    }

    private Map<BigDecimal, List<SettlementItemModel>> mappingByVendorId(List<SettlementItemModel>
                                                                                 settlementItemModelList,
                                                                         Date dateUpload) {
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

    private void processGenerateSettlement(Map<BigDecimal, List<SettlementItemModel>> groupByVendorId) {

        GenerateSettlement generateSettlement = new GenerateSettlement();
        try {

            logger.info("number of vendor id will generate : {} ", groupByVendorId.size());
            groupByVendorId.entrySet().stream().forEach(mapperByVendorId -> {

                int totalPendapatan = mapperByVendorId.getValue()
                        .stream()
                        .mapToInt(value -> value.getOrderSubtotal().intValue()).sum();

                Merchant merchant = merchantService.findByVendorId(mapperByVendorId.getKey().longValue());

                if (merchant != null) {
                    SettlementModel settlementModel = SettlementModel
                            .builder()
                            .bank(merchant.getBankName())
                            .vendorName(merchant.getName())
                            .noRek(String.valueOf(merchant.getAccountNumber()))
                            .title(TITLE_GENERATE_REPORT)
                            .totalPendapatan(new BigDecimal(totalPendapatan))
                            .items(mapperByVendorId.getValue())
                            .build();

                    generateSettlement.generateExcel(settlementModel);

                    logger.info("success generate report for Vendor Id : {}", merchant.getVendorId());
                }
            });

        } catch (Exception e) {
            logger.error("Error process generate report ", e);
        }
    }

    private Object cellValue(Cell cell) {
        Object cellValue;
        switch (cell.getCellType()) {
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    cellValue = cell.getDateCellValue();
                } else {
                    cellValue = cell.getNumericCellValue();
                }
                break;
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case FORMULA:
                cellValue = cell.getCellFormula();
                break;
            case BOOLEAN:
                cellValue = cell.getBooleanCellValue();
                break;
            case ERROR:
                throw new IllegalArgumentException();
            case BLANK:
                return "";
            default:
                return null;
        }

        return cellValue;
    }

}
