package com.bp.learnblog.service.impl.admin;

import com.bp.learnblog.dao.admin.PermissionRepository;
import com.bp.learnblog.entity.admin.Permission;
import com.bp.learnblog.service.admin.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author DH
 */
@Slf4j
@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource
    private PermissionRepository permissionRepository;

    /**
     * 根据id查找权限
     *
     * @param id
     * @return
     */
    @Override
    public Permission findById(Long id) {
        return permissionRepository.findById(id).orElse(null);
    }

    /**
     * 查询所有权限列表
     *
     * @return
     */
    @Override
    public Iterable<Permission> findAll() {
        return permissionRepository.findAll();
    }

    /**
     * 根据角色id查找权限列表
     *
     * @param roleId
     * @return
     */
    @Override
    public List<Permission> findAllByRoleId(Long roleId) {
        log.info("findAllByRoleId: roleId-{}", roleId);
        return permissionRepository.findAllByRoleId(roleId);
    }
}
