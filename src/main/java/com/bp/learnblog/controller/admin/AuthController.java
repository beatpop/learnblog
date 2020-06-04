package com.bp.learnblog.controller.admin;

import com.bp.learnblog.entity.admin.User;
import com.bp.learnblog.response.ResponseContent;
import com.bp.learnblog.service.admin.UserService;
import com.bp.learnblog.util.JwtUtil;
import com.bp.learnblog.util.ResponseUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 验证controller
 *
 * @author DH
 */
@RestController
public class AuthController {

    @Resource
    private UserService userService;

    /**
     * 登录
     *
     * @param user
     * @return
     */
    @PostMapping("/login")
    public ResponseContent login(@RequestBody User user) {
        if (user != null && userService.checkUserPassword(user.getUsername(), user.getPassword())) {
            User currentUser = userService.findByUsername(user.getUsername());
            return ResponseUtil.success(JwtUtil.signToken(currentUser.getUsername(), currentUser.getPassword()));
        }

        return ResponseUtil.error(1, "用户名或密码有误！");
    }

    /**
     * 注册
     *
     * @param user
     * @return
     */
    @PostMapping("/register")
    public ResponseContent register(@RequestBody User user) {

        // 判断用户是否已存在
        if (userService.findByUsername(user.getUsername()) != null) {
            return ResponseUtil.error(3, "用户名已存在，请重新输入！");
        }

        User newUser = userService.createUser(user);
        if (newUser != null) {
            return ResponseUtil.success(newUser);
        }

        return ResponseUtil.error(2, "用户注册有误，请重新操作！");
    }

    /**
     * 未授权跳转的接口
     *
     * @param message 错误信息
     * @return
     */
    @GetMapping("/unauthorized/{message}")
    public ResponseContent unAuthorization(@PathVariable("message") String message) {
        return ResponseUtil.error(401, message);
    }
}
