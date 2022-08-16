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
public class District extends AbstractAuditing{
    @Column(length = 30, unique = true)
    @Getter(onMethod_ = @NotEmpty(message = "Name is required"))
    private String name;
    @Column(length = 30, unique = true)
    @Getter(onMethod_ = @NotEmpty(message = "Bangla name is required"))
    private String bnName;
    private String lat;
    private String lon;
    private String url;
    @ManyToOne
    private Division division;
}
