package co.id.gooddoctor.gundala.domain.settlement.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table
public class Settlement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String fileName;
    Date fileDate;
    long vendorId;
    String vendorName;
    String filePath;


    @CreatedDate
    LocalDate createdDate;

    @LastModifiedDate
    LocalDate updatedDate;

}
