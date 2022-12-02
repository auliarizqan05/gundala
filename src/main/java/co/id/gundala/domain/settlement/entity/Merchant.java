package co.id.gundala.domain.settlement.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table
@Data
public class Merchant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(unique = true)
    long vendorId;
    String vendorName;
    String accountNumber;
    String companyName;
    String branchName;
    String bankName;
    double commissionPercentage;

    @CreationTimestamp
    Timestamp createdDate;

    @UpdateTimestamp
    LocalDate updatedDate;
}
