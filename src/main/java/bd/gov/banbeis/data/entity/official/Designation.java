package bd.gov.banbeis.data.entity.official;

import bd.gov.banbeis.data.entity.AbstractAuditing;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Designation extends AbstractAuditing {
    private String nameBn;
    private String nameEn;
    private Integer grade;
}
