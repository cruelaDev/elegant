package org.example.elegant.user.permission;

import org.example.elegant.common.repository.CommonRepository;
import org.example.elegant.user.permission.entity.Permission;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends CommonRepository<Permission,String> {
}
