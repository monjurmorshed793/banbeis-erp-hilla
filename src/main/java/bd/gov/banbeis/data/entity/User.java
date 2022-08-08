package bd.gov.banbeis.data.entity;

import bd.gov.banbeis.data.RoleEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name = "application_user", indexes = @Index(columnList = "username, fullName, email"))
public class User extends AbstractAuditing {

    @Column(unique = true, nullable = false, length = 25)
    @Getter(onMethod_ = @NotEmpty(message = "Username is required"))
    private String username;

    @Column(nullable = false, length = 50)
    @Getter(onMethod_ = @NotEmpty(message = "Full Name is required"))
    private String fullName;

    @Column(nullable = false, length = 50, unique = true)
    @Getter(onMethod_ = {@NotEmpty(message = "Email is required"), @Email(message = "Must be a valid email")})
    private String email;

    @Transient
    @Getter(onMethod_ = @NotEmpty(message = "Password is required"))
    private String password;

    @Transient
    private String confirmPassword;

    @JsonIgnore
    private String hashedPassword;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;
    @Lob
    private String profilePictureUrl;
}
