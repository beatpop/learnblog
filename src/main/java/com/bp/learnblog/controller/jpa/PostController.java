package com.bp.learnblog.controller.jpa;

import com.bp.learnblog.response.ResponseContent;
import com.bp.learnblog.service.jpa.PostService;
import com.bp.learnblog.util.ResponseUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 博客文章控制器
 *
 * @author DH
 */
@RequestMapping("/posts")
@RestController
public class PostController {

    @Resource
    private PostService postService;

    /**
     * 获取所有博客文章
     *
     * @return
     */
    @GetMapping({"", "/"})
    public ResponseContent index() {
        return ResponseUtil.success(postService.findAll());
    }

    @GetMapping("/findbyuser")
    public ResponseContent findAllByUser(@RequestParam(name = "user_id", required = true) Long userId) {
        return ResponseUtil.success(postService.findAllByUserId(userId));
    }
}
