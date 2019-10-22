package co.id.gooddoctor.gundala.domain.user.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Table
@Entity
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String name;
    String description;
    String status;

    @CreationTimestamp
    LocalDate createdDate;

    @UpdateTimestamp
    LocalDate updatedDate;
}
