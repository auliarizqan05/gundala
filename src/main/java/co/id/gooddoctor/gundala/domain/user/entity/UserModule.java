package co.id.gooddoctor.gundala.domain.user.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
@Data
public class UserModule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String description;
    String status;
    @JoinColumn(name = "user_id")
    @ManyToOne
    User users;
    @JoinColumn(name = "modules_id")
    @ManyToOne
    Module modules;
    @JoinColumn(name = "roles_id")
    @ManyToOne
    Role roles;

    @CreationTimestamp
    LocalDate createdDate;

    @UpdateTimestamp
    LocalDate updatedDate;
}
