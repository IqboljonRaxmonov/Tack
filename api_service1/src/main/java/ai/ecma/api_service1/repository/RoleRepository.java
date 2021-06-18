package ai.ecma.api_service1.repository;

import ai.ecma.api_service1.entity.Role;
import ai.ecma.api_service1.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByRoleName(RoleName roleName);
}
