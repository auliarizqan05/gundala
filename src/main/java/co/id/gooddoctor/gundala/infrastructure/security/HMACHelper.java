package co.id.gooddoctor.gundala.infrastructure.security;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

@Component
@Slf4j
public class HMACHelper {

    @Value("${api.key}")
    String apiKey;

    @Value("${api.id}")
    String apiId;

    public boolean calculateHMACSignature(String time, String hmac) {

        boolean status = false;
        try {
            log.info("Enter to calculateHmacSignature ", apiId);
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(apiKey.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            String concatMessage = new StringBuilder()
                    .append(time)
                    .append(apiId)
                    .toString();

            String hash = Base64.encodeBase64String(sha256_HMAC.doFinal(concatMessage.getBytes()));

            status = StringUtils.equals(hmac, hash);
            log.info("Success to match signature");
        } catch (Exception e) {
            log.error("Error to signature", e);
        }

        return status;
    }
}
