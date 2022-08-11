package bd.gov.banbeis.data.entity.profile;


import bd.gov.banbeis.data.entity.AbstractAuditing;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class GeneralInformation extends AbstractAuditing {

}
