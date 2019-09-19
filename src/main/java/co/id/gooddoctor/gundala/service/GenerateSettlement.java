package co.id.gooddoctor.gundala.service;

import co.id.gooddoctor.gundala.model.GenSettlementDto;
import co.id.gooddoctor.gundala.model.GenSettlementItemDto;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class GenerateSettlement {
    public void generateExcel(final GenSettlementDto genSettlementDto) throws IOException {
        List<GenSettlementDto> genSettlementDtos = new ArrayList<>();
        genSettlementDtos.add(genSettlementDto);

        try (InputStream is = new ClassPathResource("template2.xls").getInputStream()) {
            try (OutputStream os = new FileOutputStream(genSettlementDtos.get(0).getPeriodTrf().concat("-").concat(genSettlementDtos.get(0).getVendorName().concat(".xls")))) {
                Context context = new Context();
                context.putVar("settlements", genSettlementDtos);
                context.putVar("settlementItems", genSettlementDtos.get(0).getItems());
                JxlsHelper.getInstance().processTemplate(is, os, context);
            }
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
