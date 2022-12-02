package co.id.gundala;

import co.id.gundala.infrastructure.util.JsonUtil;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.HashMap;
import java.util.Map;


//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Transactional(propagation = Propagation.NOT_SUPPORTED)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class GundalaApplicationTests {

    private static Logger logger = LoggerFactory.getLogger(GundalaApplicationTests.class);

    @LocalServerPort
    private int port;

    @Test
    public void call_CreateMerchant() {

        Map<String, String> merchant = new HashMap<>();
        merchant.put("name", "anggito");
        merchant.put("address", "noble house");

        assertNull(merchant);
        logger.info("isinya apa {} ", JsonUtil.objectToJson(merchant));
    }


}
