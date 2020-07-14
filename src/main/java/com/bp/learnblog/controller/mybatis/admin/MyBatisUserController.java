package com.bp.learnblog.controller.mybatis.admin;

import com.bp.learnblog.response.ResponseContent;
import com.bp.learnblog.service.mybatis.admin.MybatisUserService;
import com.bp.learnblog.util.ResponseUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**UserController
 *
 * @author DH
 */
@RequestMapping("/mybatis/users")
@RestController
public class MyBatisUserController {

    @Resource
    private MybatisUserService userService;

    @GetMapping
    public ResponseContent findAll() {
        return ResponseUtil.success(userService.findAll());
    }

    @GetMapping("/{user_id}")
    public ResponseContent findByUserId(@PathVariable("user_id") Long userId) {
        return ResponseUtil.success(userService.findByUserId(userId));
    }
}
