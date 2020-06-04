package com.bp.learnblog.controller.admin;

import com.bp.learnblog.response.ResponseContent;
import com.bp.learnblog.service.admin.RoleService;
import com.bp.learnblog.util.ResponseUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 角色控制器
 *
 * @author DH
 */
@RequestMapping("/roles")
@RestController
public class RoleController {

    @Resource
    private RoleService roleService;

    /**
     * 查找所有角色
     *
     * @return
     */
    @GetMapping
    public ResponseContent findAll() {
        return ResponseUtil.success(roleService.findAll());
    }

    /**
     * 根据用户id查询所属角色
     *
     * @param userId 用户id
     * @return
     */
    @GetMapping(params = "user_id")
    public ResponseContent findAllByUserId(@RequestParam("user_id") Long userId) {
        return ResponseUtil.success(roleService.findAllByUserId(userId));
    }

    /**
     * 根据 id 查询角色详情
     *
     * @param id 角色id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseContent roleInfo(@PathVariable("id") Long id) {
        return ResponseUtil.success(roleService.findById(id));
    }

    /**
     * 删除角色
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseContent delete(@PathVariable("id") Long id) {
        boolean res = roleService.deleteById(id);
        if (res) {
            return ResponseUtil.success();
        }
        return ResponseUtil.error(3, "角色删除有误！");
    }
}
