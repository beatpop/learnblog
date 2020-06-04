package com.bp.learnblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Index 控制器
 *
 * @author DH
 */
@Controller
public class IndexController {

    /**
     * 访客
     * @return
     */
    @RequestMapping("/guest/welcome")
    @ResponseBody
    public String guest() {
        return "欢迎您访问本页面！";
    }

    /**
     * 首页
     * @return
     */
    @GetMapping({"", "/", "/index"})
    public String index() {
        return "index";
    }
}
