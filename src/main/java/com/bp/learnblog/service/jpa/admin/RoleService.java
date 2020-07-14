package com.bp.learnblog.service.jpa.admin;

import com.bp.learnblog.entity.jpa.admin.Role;
import java.util.List;

/**
 * 角色Service
 *
 * @author DH
 */
public interface RoleService {

    /**
     * 根据id查找角色
     *
     * @param id 角色id
     * @return
     */
    public Role findById(Long id);

    /**
     * 查找所有的角色
     *
     * @return
     */
    public Iterable<Role> findAll();

    /**
     * 根据userId查询所有角色
     *
     * @param userId 用户id
     * @return
     */
    public List<Role> findAllByUserId(Long userId);

    /**
     * 创建角色
     * @param role
     * @return
     */
    public Role createRole(Role role);

    /**
     * 根据id删除角色
     *
     * @param id
     * @return
     */
    public boolean deleteById(Long id);
}
