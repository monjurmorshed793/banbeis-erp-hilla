package bd.gov.banbeis.data.repository;

import bd.gov.banbeis.data.entity.Division;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DivisionRepository extends JpaRepository<Division, UUID> {
}
