package co.id.gundala.domain.settlement.service;

import co.id.gundala.domain.settlement.entity.SettlementFile;
import co.id.gundala.domain.settlement.model.SettlementModel;
import co.id.gundala.infrastructure.util.ConstantUtil;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class GenerateSettlement {

    private static Logger logger = LoggerFactory.getLogger(GenerateSettlement.class);
    private final static DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");


    public SettlementFile generateExcel(SettlementModel settlementModel, String filePath, Date dateupload) {

        logger.info("begin to generate report {} ", settlementModel.getVendorName());
        List<SettlementModel> settlementModels = new ArrayList<>();
        settlementModels.add(settlementModel);

        File fileName = new File(new StringBuilder()
                .append(filePath)
                .append(DATE_FORMAT.format(dateupload))
                .append("-")
                .append(settlementModel.getVendorName())
                .append(".xlsx")
                .toString());

        try (InputStream is = new ClassPathResource(ConstantUtil.TEMPLATE_NAME).getInputStream();
             OutputStream os = new FileOutputStream(fileName)) {

            Context context = new Context();
            context.putVar("settlements", settlementModels);
            context.putVar("settlementItems", settlementModels.get(0).getItems());

            JxlsHelper.getInstance().processTemplate(is, os, context);

            return mapperSettlementFile(fileName, settlementModel);
        } catch (IOException e) {
            logger.error("cannot create excel template vendor id = {}, name = {}",
                    settlementModel.getVendorName(), settlementModel.getVendorId(), e);
            throw new IllegalArgumentException("Something wrong when generate report (" + e.getMessage() + ") for vendor ID = "
                    + settlementModel.getVendorId());
        }
    }

    private SettlementFile mapperSettlementFile(File fileName, SettlementModel settlementModel) {
        SettlementFile settlementFile = new SettlementFile();

        settlementFile.setFileName(fileName.getName());
        settlementFile.setFilePath(fileName.getParent());
        settlementFile.setFileDate(new Date());
        settlementFile.setVendorId(settlementModel.getVendorId());
        settlementFile.setVendorName(settlementModel.getVendorName());

        return settlementFile;
    }


}
