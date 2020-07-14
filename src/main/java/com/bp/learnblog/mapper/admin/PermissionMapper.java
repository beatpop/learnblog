package com.bp.learnblog.mapper.admin;

import com.bp.learnblog.entity.mybatis.admin.Permission;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * PermissionMapper
 *
 * @author DH
 */
@Mapper
public interface PermissionMapper {
    /**
     * 查找所有权限
     *
     * @return
     */
    List<Permission> findAll();

    /**
     * 根据角色 ID 查询权限列表
     *
     * @param roleId
     * @return
     */
    List<Permission> findAllByRoleId(@Param("roleId") Long roleId);
}
