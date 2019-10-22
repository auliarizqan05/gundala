package co.id.gooddoctor.gundala.domain.settlement.service;

import co.id.gooddoctor.gundala.domain.settlement.model.SettlementModel;
import co.id.gooddoctor.gundala.infrastructure.util.ConstantUtil;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GenerateSettlement {

    private static Logger logger = LoggerFactory.getLogger(GenerateSettlement.class);
    private final static DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public boolean generateExcel(SettlementModel settlementModel, String filePath) {
        List<SettlementModel> settlementModels = new ArrayList<>();
        settlementModels.add(settlementModel);

        String fileName = new StringBuilder()
                .append(filePath)
                .append(DATE_FORMAT.format(new Date()))
                .append("-")
                .append(settlementModel.getVendorName())
                .append(".xls")
                .toString();

        try (InputStream is = new ClassPathResource(ConstantUtil.TEMPLATE_NAME).getInputStream();
             OutputStream os = new FileOutputStream(fileName)) {

            Context context = new Context();
            context.putVar("settlements", settlementModels);
            context.putVar("settlementItems", settlementModels.get(0).getItems());
            JxlsHelper.getInstance().processTemplate(is, os, context);

            return true;
        } catch (IOException e) {
            logger.error("cannot create excel template ", e);
            return false;
        }
    }

}
