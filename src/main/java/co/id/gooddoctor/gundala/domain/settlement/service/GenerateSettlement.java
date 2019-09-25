package co.id.gooddoctor.gundala.domain.settlement.service;

import co.id.gooddoctor.gundala.domain.settlement.model.SettlementModel;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final static String TEMPLATE_NAME = "template2.xls";
    private final static DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public boolean generateExcel(SettlementModel settlementModel) {
        List<SettlementModel> settlementModels = new ArrayList<>();
        settlementModels.add(settlementModel);

        String fileName = new StringBuilder()
                .append(DATE_FORMAT.format(new Date()))
                .append("-")
                .append(settlementModel.getVendorName())
                .append(".xls")
                .toString();

        try (InputStream is = new ClassPathResource(TEMPLATE_NAME).getInputStream();
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

//    public static void main(String[] args) throws IOException {
//        List<GenSettlementDto> genSettlementDtos = new ArrayList<>();
//        List<GenSettlementItemDto> genSettlementItemDtos = new ArrayList<>();
//        GenSettlementDto genSettlementDto = new GenSettlementDto();
//        genSettlementDto.setTitle("ini judul");
//        genSettlementDto.setVendorName("ini vendor name");
//        genSettlementDto.setBank("ini bank");
//        genSettlementDto.setNoRek("ini no rek");
//        genSettlementDto.setPeriodTrf("ini perioe trf");
//        genSettlementDto.setTotalIncome("ini total income");
//        genSettlementDto.setTotalLebihBayar("ini total lebih bayar");
//        genSettlementDto.setTotalPay("ini total pay");
//
//        GenSettlementItemDto genSettlementItemDto1 = new GenSettlementItemDto();
//        genSettlementItemDto1.setOrderCreated("ini order created");
//        genSettlementItemDto1.setOrderId("ini order id");
//        genSettlementItemDto1.setOrderSubtotal("ini order subtotal");
//        genSettlementItemDto1.setPayDate("ini pay date");
//        genSettlementItemDto1.setPayStatus("ini pay status");
//        genSettlementItemDto1.setStoreId("ini store id");
//        genSettlementItemDto1.setStoreName("ini store name");
//        genSettlementItemDto1.setUserId("ini user id");
//        genSettlementItemDto1.setVendorId("ini Vendor id");
//        genSettlementItemDto1.setVendorName("ini Vendor name");
//
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//        genSettlementItemDtos.add(genSettlementItemDto1);
//
//
//        genSettlementDto.setItems(genSettlementItemDtos);
//        genSettlementDtos.add(genSettlementDto);
//
//
//        GenerateSettlement generateSettlement = new GenerateSettlement();
//        generateSettlement.generateExcel(genSettlementDto);
//
//    }
}
