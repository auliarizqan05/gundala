package co.id.gundala.domain.settlement.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigInteger;

@Data
@JsonIgnoreProperties
public class MerchantDTO {

    String vendorName;
    String accountNumber;
    String branchName;
    String companyName;
    String bankName;
    double commissionPercentage;
    BigInteger vendorId;
}
