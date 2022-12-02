package co.id.gundala;

import co.id.gundala.infrastructure.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;

@SpringBootTest
@Slf4j
public class UserServiceTest {

    @Autowired
    ActiveDirectoryLdapAuthenticationProvider activeDirectoryLdapAuthenticationProvider;

    @Test
    public void call_ADFunction() {
        try {

            log.info("math random {} ", Math.random());
//            List<GrantedAuthority> grantedAuths = new ArrayList<>();
//            grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
//            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
//                    "local/aulia.rizqan", "P@ssw0rd" );

//            "it.corp@local.id", "P@ssw0rd!"

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    "local", "2019");

            Authentication auth = activeDirectoryLdapAuthenticationProvider.authenticate(authentication);

            log.info("a {} ", JsonUtil.objectToJson(auth));
        } catch (Exception e) {
            log.info("message error {} ", e.getMessage());
            e.printStackTrace();
        }
    }

//    @Test
//    public void call_ConnRedis() {
//        try {
//            //Connecting to Redis server on localhost
//            Jedis jedis = new Jedis("localhost");
//            jedis.set("tutorial", "Redis tutorial");
//            log.info("Connection to server sucessfully");
//            //check whether server is running or not
//            log.info("Server is running: "+jedis.get("tutorial"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
