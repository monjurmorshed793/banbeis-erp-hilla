package bd.gov.banbeis.data.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name="role")
public class Role extends AbstractEntity{
    private String role;
}
