package bd.gov.banbeis.data.entity;

import bd.gov.banbeis.data.entity.official.Department;
import bd.gov.banbeis.data.entity.official.Designation;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(indexes ={
        @Index(columnList = "employeeId"),
        @Index(columnList = "nameEn"),
        @Index(columnList = "nameBn"),
        @Index(columnList = "department"),
        @Index(columnList = "designation")
})
public class Employee extends AbstractAuditing{
    @Column(length = 25,unique = true)
    @Getter(onMethod_ = @NotEmpty(message = "Employee id is required"))
    private String employeeId;
    @Column(length = 50)
    @Getter(onMethod_ = @NotEmpty(message = "English name is required"))
    private String nameEn;
    @Column(length = 50)
    @Getter(onMethod_ = @NotEmpty(message = "Bangla name is required"))
    private String nameBn;
    @ManyToOne
    @Getter(onMethod_ = @NotEmpty(message = "Department is required"))
    private Department department;
    @ManyToOne
    @Getter(onMethod_ = @NotEmpty(message = "Designation is required"))
    private Designation designation;
}
