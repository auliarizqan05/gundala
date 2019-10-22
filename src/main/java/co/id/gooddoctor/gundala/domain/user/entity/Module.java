package co.id.gooddoctor.gundala.domain.user.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Table
@Entity
@Data
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String name;
    String url;
    String description;
    String status;

    @CreationTimestamp
    LocalDate createdDate;

    @UpdateTimestamp
    LocalDate updatedDate;
}
