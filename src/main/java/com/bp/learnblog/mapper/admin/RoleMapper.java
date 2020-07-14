package com.bp.learnblog.mapper.admin;

import com.bp.learnblog.entity.mybatis.admin.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * RoleMapper
 *
 * @author DH
 */
@Mapper
public interface RoleMapper {
    /**
     * 根据角色ID查询角色详情
     *
     * @param roleId
     * @return
     */
    Role findOne(@Param("roleId") Long roleId);

    /**
     * 查找所有的角色
     *
     * @return
     */
    List<Role> findAll();

    /**
     * 根据权限 ID 查找角色列表
     *
     * @param permissionId
     * @return
     */
    List<Role> findAllByPermissionId(@Param("permissionId") Long permissionId);

    /**
     * 根据用户ID查找角色列表
     *
     * @param userId
     * @return
     */
    List<Role> findAllByUserId(@Param("userId") Long userId);
}
