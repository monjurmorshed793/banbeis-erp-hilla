package bd.gov.banbeis.data.entity.profile;


import bd.gov.banbeis.data.entity.AbstractAuditing;
import bd.gov.banbeis.data.entity.Employee;
import bd.gov.banbeis.data.entity.official.Designation;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class GeneralInformation extends AbstractAuditing {
    private LocalDate dob;
    private String nid;
    private String nationality;
    private String email;
    private String mobile;
    private String officeTelephone;
    @ManyToOne
    private Designation originalDesignation;
    @ManyToOne
    private Designation presentPost;
    String presentWorkingStation;
    private String eTin;
    @Enumerated(EnumType.STRING)
    private GenderType gender;
    @Enumerated(EnumType.STRING)
    private BloodGroupType bloodGroup;
    @Enumerated
    private MartialType martialType;
    @Enumerated
    private ReligionType religionType;
    @OneToOne
    private Employee employee;
}
