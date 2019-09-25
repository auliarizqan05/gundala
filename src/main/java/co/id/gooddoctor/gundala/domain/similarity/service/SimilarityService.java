package co.id.gooddoctor.gundala.domain.similarity.service;

import co.id.gooddoctor.gundala.domain.settlement.model.MimsDto;
import info.debatty.java.stringsimilarity.JaroWinkler;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class SimilarityService {
    public void compareDatabaseDrugsToVendorDrugs(final BigDecimal threshold,
                                                  final File currentFile,
                                                  final File databaseDrugsFile) throws IOException, InvalidFormatException {

        List<MimsDto> mimsDtos = new ArrayList<>();
        XSSFWorkbook workbookCurrent = new XSSFWorkbook(currentFile);
        XSSFSheet worksheetCurrent = workbookCurrent.getSheetAt(0);

        XSSFWorkbook workbookMims = new XSSFWorkbook(databaseDrugsFile);
        XSSFSheet worksheetMims = workbookMims.getSheetAt(0);

        JaroWinkler jaroWinkler = new JaroWinkler();
        Random random = new Random();

        for (int i = 0; i < worksheetCurrent.getPhysicalNumberOfRows(); i++) {
            MimsDto mimsDto = new MimsDto();
            List<String> rowNumMims = new ArrayList<>();

            XSSFRow rowCurrent = worksheetCurrent.getRow(i);
            System.out.println("ROW KE: " + i);

            String productNameCurrent = rowCurrent.getCell(0).getStringCellValue();
            if (null == productNameCurrent || productNameCurrent.equals("")) {
                continue;
            }

            productNameCurrent = productNameCurrent.toLowerCase();
            String similarityPercent = "Need To Check or Not Found in Mims";
            BigDecimal tempSililarityDec = BigDecimal.ZERO;

            for (int j = 0; j < worksheetMims.getPhysicalNumberOfRows(); j++) {
                XSSFRow rowMims = worksheetMims.getRow(j);
                String productNameMims = rowMims.getCell(0).getStringCellValue().toLowerCase();

                BigDecimal similarityDec = new BigDecimal(jaroWinkler.similarity(productNameCurrent, productNameMims)).setScale(2, RoundingMode.DOWN);

                if (similarityDec.compareTo(threshold) >= 0) {
                    if (similarityDec.compareTo(tempSililarityDec) > 0) {
                        similarityPercent = similarityDec.multiply(new BigDecimal(100)) + "%";
                        tempSililarityDec = similarityDec;
                    }
                    rowNumMims.add((similarityDec.multiply(new BigDecimal(100)) + "%").concat("-").concat(productNameMims).concat("-").concat(String.valueOf(j + 1)));
                }
            }

            mimsDto.setProductName(productNameCurrent);
            mimsDto.setSimilarity(similarityPercent);
            rowNumMims = rowNumMims.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
            mimsDto.setRowMims(rowNumMims.toString());

            mimsDtos.add(mimsDto);
        }

        Workbook wb = new HSSFWorkbook();
        OutputStream fileOut = new FileOutputStream("similarity" + random.nextInt(5) + ".xls");

        Sheet sheet = wb.createSheet("Similarity_Result");
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Nama_sp");
        header.createCell(1).setCellValue("Highest Similarity");
        header.createCell(2).setCellValue("Product Mims (desc:{similarity}-{product_name}-{row_in_mims}");
        int counter = 1;

        for (MimsDto mims : mimsDtos) {
            Row row = sheet.createRow(counter++);
            row.createCell(0).setCellValue(mims.getProductName());
            row.createCell(1).setCellValue(mims.getSimilarity());
            row.createCell(2).setCellValue(mims.getRowMims());
        }

        wb.write(fileOut);
    }
}