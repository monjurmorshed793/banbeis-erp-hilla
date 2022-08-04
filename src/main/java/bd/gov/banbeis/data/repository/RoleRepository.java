package bd.gov.banbeis.data.repository;

import bd.gov.banbeis.data.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
}
