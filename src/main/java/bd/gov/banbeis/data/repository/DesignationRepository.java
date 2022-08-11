package bd.gov.banbeis.data.repository;

import bd.gov.banbeis.data.entity.official.Designation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DesignationRepository extends JpaRepository<Designation, UUID> {

}
