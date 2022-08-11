package bd.gov.banbeis.data.repository;

import bd.gov.banbeis.data.entity.official.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DepartmentRepository extends JpaRepository<Department, UUID> {
}
