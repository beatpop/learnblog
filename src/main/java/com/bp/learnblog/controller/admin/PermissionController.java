package com.bp.learnblog.controller.admin;

import com.bp.learnblog.response.ResponseContent;
import com.bp.learnblog.service.admin.PermissionService;
import com.bp.learnblog.util.ResponseUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 权限控制器
 *
 * @author DH
 */
@RequestMapping("/permissions")
@RestController
public class PermissionController {

    @Resource
    private PermissionService permissionService;

    /**
     * 查找所有权限
     *
     * @return
     */
    @GetMapping
    public ResponseContent findAll() {
        return ResponseUtil.success(permissionService.findAll());
    }

    /**
     * 根据id查找详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseContent findById(@PathVariable("id") Long id) {
        return ResponseUtil.success(permissionService.findById(id));
    }

    /**
     * 根据角色id查找权限列表
     *
     * @param roleId
     * @return
     */
    @GetMapping(params = {"role_id"})
    public ResponseContent findByRoleId(@RequestParam("role_id") Long roleId) {
        return ResponseUtil.success(permissionService.findAllByRoleId(roleId));
    }
}
