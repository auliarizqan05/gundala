package co.id.gooddoctor.gundala.infrastructure.util;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ExcelUtil {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy HH:mm");

    public static Object cellValue(Cell cell) {
        Object cellValue;
        switch (cell.getCellType()) {
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    cellValue = DATE_FORMAT.format(cell.getDateCellValue());
                } else {
                    cellValue = Double.valueOf(cell.getNumericCellValue());
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
            case BLANK:
                return "";
            case ERROR:
                throw new IllegalStateException("Unexpected value: " + cell.getCellType());
            default:
                return null;
        }

        return cellValue;
    }
}
