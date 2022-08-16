package bd.gov.banbeis.data.entity.profile;

import bd.gov.banbeis.data.entity.*;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Address extends AbstractAuditing {
    @Enumerated
    private AddressType addressType;
    @Getter(onMethod_ = @NotEmpty(message = "Village/House/Road information is required"))
    private String road;
    @Column(length = 30)
    @Getter(onMethod_ = @NotEmpty(message = "Post office is required"))
    private String postOffice;
    @Column(length = 6)
    @Getter(onMethod_ = @NotEmpty(message = "Postal code is required"))
    private Integer postalCode;
    @ManyToOne
    @Getter(onMethod_=@NotEmpty(message = "District is required"))
    private District district;
    @ManyToOne
    @Getter(onMethod_ = @NotEmpty(message = "Upazila is required"))
    private Upazila upazila;
    @ManyToOne
    private Employee employee;
}
