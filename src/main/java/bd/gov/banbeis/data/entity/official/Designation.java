package bd.gov.banbeis.data.entity.official;

import bd.gov.banbeis.data.entity.AbstractAuditing;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Designation extends AbstractAuditing {
    @Column(length = 50)
    @Getter(onMethod_ = @NotEmpty(message = "Bangla name is required"))
    private String nameBn;
    @Column(length = 50)
    @Getter(onMethod_ = @NotEmpty(message = "English name is required"))
    private String nameEn;
    @Column(length = 2)
    @Getter(onMethod_ = @NotEmpty(message = "Grade is required"))
    private Integer grade;
}
