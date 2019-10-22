package co.id.gooddoctor.gundala;

import co.id.gooddoctor.gundala.domain.settlement.model.SettlementItemModel;
import co.id.gooddoctor.gundala.domain.settlement.model.SettlementModel;
import co.id.gooddoctor.gundala.domain.settlement.service.GenerateSettlement;
import co.id.gooddoctor.gundala.domain.settlement.service.SettlementProcessService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SettlementProcessServiceTest {

    @Autowired
    SettlementProcessService settlementProcessService;

    @Value("${file.generate.path}")
    private String filePath;

    @Test
    public void generate_ExcelForReport() {

        List<SettlementModel> genSettlementDtos = new ArrayList<>();
        List<SettlementItemModel> genSettlementItemDtos = new ArrayList<>();
        SettlementModel genSettlementDto = new SettlementModel();
        genSettlementDto.setTitle("ini judul");
        genSettlementDto.setVendorName("ini vendor name");
        genSettlementDto.setBank("ini bank");
        genSettlementDto.setNoRek("ini no rek");
        genSettlementDto.setPeriodTrf("ini perioe trf");
        genSettlementDto.setTotalPendapatan(new BigDecimal(20000));
        genSettlementDto.setTotalLebihBayar(new BigDecimal(20000));
        genSettlementDto.setTotalPembayaranSaatIni(new BigDecimal(20000));

        SettlementItemModel genSettlementItemDto1 = new SettlementItemModel();
        genSettlementItemDto1.setOrderCreatedDate(new Date());
        genSettlementItemDto1.setOrderId(new BigDecimal(2120202000));
        genSettlementItemDto1.setOrderSubtotal(new BigDecimal(20000));
        genSettlementItemDto1.setPaymentDate(new Date());
        genSettlementItemDto1.setPaymentStatus("ini pay status");
        genSettlementItemDto1.setStoreId(new BigDecimal(2102020202));
        genSettlementItemDto1.setStoreName("ini store name");
        genSettlementItemDto1.setUserId(new BigDecimal(212032303));
        genSettlementItemDto1.setVendorId(new BigDecimal(22141134));
        genSettlementItemDto1.setVendorName("ini Vendor name");

        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);
        genSettlementItemDtos.add(genSettlementItemDto1);

        genSettlementDto.setItems(genSettlementItemDtos);
        genSettlementDtos.add(genSettlementDto);

        GenerateSettlement generateSettlement = new GenerateSettlement();
        generateSettlement.generateExcel(genSettlementDto, filePath);

    }


}
