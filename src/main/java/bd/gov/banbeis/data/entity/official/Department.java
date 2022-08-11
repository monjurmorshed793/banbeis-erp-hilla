package bd.gov.banbeis.data.entity.official;

import bd.gov.banbeis.data.entity.AbstractAuditing;
import lombok.Data;
import lombok.Getter;
import org.atmosphere.config.service.Get;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.util.Set;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
public class Department extends AbstractAuditing {
    @Column(unique = true, nullable = false)
    @Getter(onMethod_ = @NotEmpty(message = "English name is required"))
    private String nameEn;
    @Column(unique = true, nullable = false)
    @Getter(onMethod_ = @NotEmpty(message = "Bangla name is required"))
    private String nameBn;
    private String logoPath;
    @Transient
    private File logo;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Designation> designation;
}
