package co.id.gooddoctor.gundala.domain.settlement.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table
@Data
public class Merchant {

    @Id
    long vendorId;
    String vendorName;
    String accountNumber;
    String companyName;
    String branchName;
    String bankName;
    double commissionPercentage;

    @CreationTimestamp
    LocalDate createdDate;

    @UpdateTimestamp
    LocalDate updatedDate;
}
