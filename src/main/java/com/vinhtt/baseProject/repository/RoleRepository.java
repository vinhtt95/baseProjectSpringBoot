package com.vinhtt.baseProject.repository;

import com.vinhtt.baseProject.model.Role;
import com.vinhtt.baseProject.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
