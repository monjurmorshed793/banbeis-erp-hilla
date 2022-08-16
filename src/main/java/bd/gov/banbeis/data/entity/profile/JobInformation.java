package bd.gov.banbeis.data.entity.profile;

import bd.gov.banbeis.data.entity.AbstractAuditing;
import bd.gov.banbeis.data.entity.Employee;
import bd.gov.banbeis.data.entity.official.Office;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.File;
import java.time.LocalDate;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class JobInformation extends AbstractAuditing {
    @Enumerated(EnumType.STRING)
    @Getter(onMethod_ = @NotEmpty(message = "Service type is required"))
    private ServiceType serviceType;
    @Getter(onMethod_ = @NotEmpty(message = "Joining date is required"))
    private LocalDate dateOfJoining;
    @NotEmpty
    private LocalDate dateOfPrl;
    private LocalDate dateOfConfirmation;
    @Transient
    private File recruitmentOrderAttachment;
    @Column(length = 100)
    @NotEmpty
    private String recruitmentOrderFilePath;
    private String quota;
    @Transient
    private File quotaAttachment;
    private String quotaAttachmentFilePath;
    @OneToOne
    @NotEmpty
    private Employee employee;
    @ManyToOne
    @Getter(onMethod_ = @NotEmpty(message = "Joining station is required"))
    private Office joiningStation;
}
