package co.id.gooddoctor.gundala.domain.settlement.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigInteger;

@Data
@JsonIgnoreProperties
public class MerchantDTO {

    String name;
    BigInteger accountNumber;
    String branchName;
    String bankName;
    double commissionPercentage;
    BigInteger vendorId;
}
