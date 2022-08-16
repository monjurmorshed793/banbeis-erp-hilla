package bd.gov.banbeis.data.entity;

import lombok.Data;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Division extends AbstractAuditing{
    @Column(length = 20, unique = true)
    @Getter(onMethod_ = @NotEmpty(message = "English name is required"))
    private String name;
    @Column(length = 20, unique = true)
    @Getter(onMethod_ = @NotEmpty(message = "Bangla name is required"))
    private String bnName;
    private String url;
}
