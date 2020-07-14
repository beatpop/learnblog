package com.bp.learnblog.service.jpa.impl.admin;

import com.bp.learnblog.dao.jpa.admin.PermissionRepository;
import com.bp.learnblog.entity.jpa.admin.Permission;
import com.bp.learnblog.service.jpa.admin.PermissionService;
import com.bp.learnblog.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author DH
 */
@Slf4j
@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource
    private PermissionRepository permissionRepository;

    @Resource
    private RedisUtil redisUtil;

    /**
     * 根据id查找权限
     *
     * @param id
     * @return
     */
    @Override
    public Object findById(Long id) {
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
