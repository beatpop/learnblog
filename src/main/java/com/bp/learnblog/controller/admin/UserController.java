package com.bp.learnblog.controller.admin;

import com.bp.learnblog.entity.admin.User;
import com.bp.learnblog.response.ResponseContent;
import com.bp.learnblog.service.admin.UserService;
import com.bp.learnblog.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * User控制器
 *
 * @author DH
 */
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 根据用户名查找用户
     *
     * @param username 用户名
     * @return
     */
    @GetMapping("/findbyusername")
    public ResponseContent findByUsername(@Param("username") String username) {
        if ("".equals(username) || username == null) {
            return ResponseUtil.error(10010, "username不可为空！");
        }
        return ResponseUtil.success(userService.findByUsername(username));
    }

    /**
     * 查找所有用户
     *
     * @return
     */
    @GetMapping("/all")
    @RequiresPermissions("user:index")
    public ResponseContent findAll() {
        return ResponseUtil.success(userService.findAll());
    }

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    @PostMapping("/add")
    @RequiresPermissions("user:store")
    public ResponseContent createUser(@RequestBody User user) {

        // 判断用户是否已存在
        if (userService.findByUsername(user.getUsername()) != null) {
            return ResponseUtil.error(3, "用户名已存在，请重新输入！");
        }

        User newUser = userService.createUser(user);
        if (newUser != null) {
            return ResponseUtil.success(newUser);
        }

        return ResponseUtil.error(2, "用户创建失败！");
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @RequiresPermissions("user:delete")
    public ResponseContent deleteUser(@PathVariable("id") Long id) {
        boolean res = userService.deleteById(id);
        if (res) {
            return ResponseUtil.success();
        }
        return ResponseUtil.error(3, "删除用户失败！");
    }
}
