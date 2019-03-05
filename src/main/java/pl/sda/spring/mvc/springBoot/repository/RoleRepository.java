package pl.sda.spring.mvc.springBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.spring.mvc.springBoot.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findRoleByRoleName(String role);
}
