package bd.gov.banbeis.data.entity.official;

import bd.gov.banbeis.data.entity.AbstractAuditing;
import bd.gov.banbeis.data.entity.District;
import bd.gov.banbeis.data.entity.Upazila;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
public class Office extends AbstractAuditing {
    @Getter(onMethod_ = @NotEmpty(message = "Name is required"))
    private String name;
    @Getter(onMethod_ = @NotEmpty(message = "Bangla name is required"))
    private String nameBn;
    @ManyToOne
    @Getter(onMethod_ = @NotEmpty(message = "Department is required"))
    private Department department;
    @ManyToOne
    private District district;
    @ManyToOne
    private Upazila upazila;
}
