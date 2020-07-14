package com.bp.learnblog.service.jpa.admin;

import com.bp.learnblog.entity.jpa.admin.Permission;

import java.util.List;

/**
 * 权限Service
 *
 * @author DH
 */
public interface PermissionService {

    /**
     * 根据id查找权限
     *
     * @param id
     * @return
     */
    public Object findById(Long id);

    /**
     * 查询所有权限列表
     *
     * @return
     */
    public Iterable<Permission> findAll();

    /**
     * 根据角色id查找权限列表
     *
     * @param roleId
     * @return
     */
    public List<Permission> findAllByRoleId(Long roleId);
}
