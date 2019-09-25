package co.id.gooddoctor.gundala.domain.settlement.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table
@Data
public class Merchant {

    @Id
    long vendorId;
    String name;
    long accountNumber;
    String branchName;
    String bankName;
    double commissionPercentage;

    @CreationTimestamp
    Date createdDate;

    @UpdateTimestamp
    Date updatedDate;
}
