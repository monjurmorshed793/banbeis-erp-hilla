package bd.gov.banbeis.data.entity.profile;

import bd.gov.banbeis.data.entity.AbstractAuditing;
import bd.gov.banbeis.data.entity.Employee;
import bd.gov.banbeis.data.entity.validation.SpouseValidator;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class FamilyInformation extends AbstractAuditing {
    @Column(length = 50)
    @Getter(onMethod_ = @NotEmpty(message = "The field is required"))
    private String fatherNameEn;
    @Column(length = 50)
    @Getter(onMethod_ = @NotEmpty(message = "The field is required"))
    private String fatherNameBn;
    @Column(length = 50)
    private String fatherNid;
    @Column(length = 50)
    @Getter(onMethod_ = @NotEmpty(message = "The field is required"))
    private String motherNameEn;
    @Column(length = 50)
    @Getter(onMethod_ = @NotEmpty(message = "The field is required"))
    private String motherNameBn;
    @Column(length = 50)
    private String motherNid;
    @Column(length = 50)
    @Getter(onMethod_ = @NotEmpty(message = "The field is required", groups = SpouseValidator.class))
    private String spouseNameEn;
    @Column(length = 50)
    @Getter(onMethod_ = @NotEmpty(message = "The field is required", groups = SpouseValidator.class))
    private String spouseNameBn;
    @Column(length = 30)
    @Getter(onMethod_ = @NotEmpty(message = "The field is required", groups = SpouseValidator.class))
    private String spouseNid;
    @Column(length = 50)
    private String spouseOccupation;
    @Column(length = 15)
    private String spouseContactNo;
    @Column(length = 30)
    private String spousePassportNo;
    @Column(length = 100)
    private String spousePresentAddress;
    private Boolean isSpousePresentAddressSameAsPresent;
    @ManyToOne
    private Employee employee;
}
