package co.id.gooddoctor.gundala.service;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

@Service
public class SettlementProcess {

    private static final String FILE_NAME = "C:\\Users\\Aulia Rizqan\\Documents\\document gdti\\century\\spu_Optimus.xlsx";

    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.getSheet("SPU Import");

}
