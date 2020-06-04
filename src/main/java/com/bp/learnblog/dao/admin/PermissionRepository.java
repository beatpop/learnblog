package com.bp.learnblog.dao.admin;

import com.bp.learnblog.entity.admin.Permission;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * PermissionDAO
 * @author DH
 */
public interface PermissionRepository extends CrudRepository<Permission, Long> {

    /**
     * 根据 roleId 查找权限列表
     *
     * @param roleId 角色id
     * @return
     */
    @Query(value = "SELECT p.* FROM permission p LEFT JOIN role_permission rp ON p.id = rp.permission_id " +
            "WHERE rp.role_id = :roleId GROUP BY p.id", nativeQuery = true)
    public List<Permission> findAllByRoleId(@Param("roleId") Long roleId);
}
