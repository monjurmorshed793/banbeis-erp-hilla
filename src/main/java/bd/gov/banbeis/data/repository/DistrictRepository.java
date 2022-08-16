package bd.gov.banbeis.data.repository;

import bd.gov.banbeis.data.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DistrictRepository extends JpaRepository<District, UUID> {
}
