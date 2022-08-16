package bd.gov.banbeis.data.entity;

import lombok.Data;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Upazila extends AbstractAuditing{
    @Column(length = 50)
    @Getter(onMethod_ = @NotEmpty(message = "Name is required"))
    private String name;
    @Column(length = 50)
    @Getter(onMethod_ = @NotEmpty(message = "Bangla name is required"))
    private String bnName;
    private String url;
    @ManyToOne
    private District district;
}
