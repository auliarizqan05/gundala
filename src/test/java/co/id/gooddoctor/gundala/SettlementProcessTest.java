package co.id.gooddoctor.gundala;

import co.id.gooddoctor.gundala.domain.settlement.service.SettlementProcess;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SettlementProcessTest {

    @Autowired
    SettlementProcess settlementProcess;

    @Test
    public void generate_ExcelForReport() {

//        settlementProcess.extractData();
    }
}
