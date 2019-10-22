package co.id.gooddoctor.gundala.domain.user.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String fullName;

    @Column(unique = true)
    String uname;
    String email;
    Date lastLogin;

    @CreationTimestamp
    LocalDate createdDate;

    @UpdateTimestamp
    LocalDate updatedDate;
}
