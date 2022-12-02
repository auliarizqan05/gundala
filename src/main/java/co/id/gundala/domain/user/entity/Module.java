package co.id.gundala.domain.user.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;

import java.sql.Timestamp;

@Table
@Entity
@Data
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String name;

    @Column(unique = true, length = 50)
    String url;
    String description;
    boolean status;

//    @JsonIgnore
//    @ManyToMany(mappedBy = "modules", fetch = FetchType.LAZY)
//    Set<Role> roles;

    @CreationTimestamp
    Timestamp createdDate;

    @UpdateTimestamp
    Timestamp updatedDate;
}
