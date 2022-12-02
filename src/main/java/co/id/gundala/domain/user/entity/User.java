package co.id.gundala.domain.user.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

@Entity
@Table
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String fullName;

    @Column(unique = true, length = 50)
    String uname;
    String pwd;
    String email;
    Date lastLogin;

    @ManyToMany(targetEntity = Role.class, cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinTable()
    Set<Role> roles;

    @CreationTimestamp
    Timestamp createdDate;

    @UpdateTimestamp
    Timestamp updatedDate;
}
