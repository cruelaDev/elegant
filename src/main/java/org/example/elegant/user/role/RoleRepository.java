package org.example.elegant.user.role;

import org.example.elegant.common.repository.CommonRepository;
import org.example.elegant.user.role.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CommonRepository<Role,String> {
    Optional<Role> findByName(String name);
}
