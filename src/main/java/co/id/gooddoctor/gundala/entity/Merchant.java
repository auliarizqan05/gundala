package co.id.gooddoctor.gundala.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;

@Data
@Entity
@Table
@JsonIgnoreProperties
public class Merchant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String name;
    BigInteger accountNumber;
    String branchName;
    String bankName;
    double commissionPercentage;
}
