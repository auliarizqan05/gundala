package co.id.gundala.domain.settlement.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Entity
@Table
public class SettlementFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String fileName;
    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    Date fileDate;
    long vendorId;
    String vendorName;
    String filePath;

    @CreationTimestamp
    Timestamp createdDate;

    @UpdateTimestamp
    Timestamp updatedDate;

}