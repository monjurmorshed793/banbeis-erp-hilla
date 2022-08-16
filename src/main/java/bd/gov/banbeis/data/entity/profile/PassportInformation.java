package bd.gov.banbeis.data.entity.profile;

import bd.gov.banbeis.data.entity.AbstractAuditing;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import java.io.File;
import java.time.LocalDate;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class PassportInformation extends AbstractAuditing {
    @Column(length = 30, unique = true)
    @Getter(onMethod_ = @NotEmpty(message = "Passport number is required"))
    private String passportNo;
    @Getter(onMethod_ = @NotEmpty(message = "Date of issue is required"))
    private LocalDate dateOfIssue;
    @Getter(onMethod_ = @NotEmpty(message = "Date of expiration is required"))
    private LocalDate dateOfExpire;
    @Transient
    private File passportCopy;
    @NotEmpty
    private String passportCopyPath;
}
