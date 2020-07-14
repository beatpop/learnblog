package com.bp.learnblog.service.jpa.impl.admin;

import com.bp.learnblog.dao.jpa.admin.RoleRepository;
import com.bp.learnblog.entity.jpa.admin.Role;
import com.bp.learnblog.service.jpa.admin.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author DH
 */
@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleRepository roleRepository;

    /**
     * 根据id查找角色
     *
     * @param id 角色id
     * @return
     */
    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    /**
     * 查找所有的角色
     *
     * @return
     */
    @Override
    public Iterable<Role> findAll() {
        return roleRepository.findAll();
    }

    /**
     * 根据userId查询所有角色
     *
     * @param userId 用户id
     * @return
     */
    @Override
    public List<Role> findAllByUserId(Long userId) {
        return roleRepository.findAllByUserId(userId);
    }

    /**
     * 创建角色
     * @param role
     * @return
     */
    @Override
    public Role createRole(Role role) {
        if (role != null) {
            Role newRole = new Role();
            newRole.setName(role.getName());
            newRole.setDescription(role.getDescription());
            newRole.setPermissions(role.getPermissions());
            roleRepository.save(newRole);
            return newRole;
        }

        return null;
    }

    /**
     * 根据id删除角色
     *
     * @param id
     * @return bool
     */
    @Override
    public boolean deleteById(Long id) {
        try {
            roleRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.warn("Delete role which id is - " + id + "faithfully. {}" + e.getMessage());
            return false;
        }
    }
}
