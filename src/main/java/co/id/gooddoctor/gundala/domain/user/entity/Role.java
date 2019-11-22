package co.id.gooddoctor.gundala.domain.user.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Table
@Entity
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(unique = true, length = 50)
    String name;
    String description;
    boolean status;

    @ManyToMany(targetEntity = Module.class, fetch = FetchType.LAZY)
    @JoinTable(
            name = "role_module",
            joinColumns = @JoinColumn(name = "roles_id"),
            inverseJoinColumns = @JoinColumn(name = "modules_id"))
    Set<Module> modules;

//    @JsonIgnore
//    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
//    Set<User> users;

    @CreationTimestamp
    Timestamp createdDate;

    @UpdateTimestamp
    Timestamp updatedDate;
}
