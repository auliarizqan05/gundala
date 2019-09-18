package co.id.gooddoctor.gundala;

import co.id.gooddoctor.gundala.util.JsonUtil;
import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
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

        logger.info("isinya apa {} ", JsonUtil.objectToJson(merchant));
        given()
                .port(port)
                .log()
                .uri()
                .contentType(ContentType.JSON)
                .body(JsonUtil.objectToJson(merchant))
                .post("gundala/merchants").getBody();
    }


}
