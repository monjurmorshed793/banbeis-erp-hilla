package bd.gov.banbeis.data.entity;

import bd.gov.banbeis.data.RoleEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;
import java.util.Set;
import javax.persistence.*;

@Entity
@Data
@Table(name = "application_user", indexes = @Index(columnList = "username, fullName, email"))
public class User extends AbstractEntity {

    @Column(unique = true, nullable = false, length = 25)
    private String username;

    @Column(nullable = false, length = 50)
    private String fullName;

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @JsonIgnore
    private String hashedPassword;

    @ManyToMany
    private List<Role> roles;
    @Lob
    private String profilePictureUrl;
}
