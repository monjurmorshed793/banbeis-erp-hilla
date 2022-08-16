package bd.gov.banbeis.data.repository;

import bd.gov.banbeis.data.entity.Upazila;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UpazilaRepository extends JpaRepository<Upazila, UUID> {
}
