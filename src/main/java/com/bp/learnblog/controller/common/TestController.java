package com.bp.learnblog.controller.common;

import com.bp.learnblog.annotation.ApiIdempotence;
import com.bp.learnblog.response.ResponseContent;
import com.bp.learnblog.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author DH
 */
@Slf4j
@RequestMapping("/test")
@Controller
public class TestController {

    @Resource
    Environment environment;

    @GetMapping({"", "/"})
    @ResponseBody
    public String test() {
        return "test";
    }

    @GetMapping("/{mod}/attribute")
    @ResponseBody
    public String testModelAttribute(@PathVariable("mod") String mod, Model model) {
        model.addAttribute("testModel", mod);
        System.out.println(model.containsAttribute("testModel"));
        return "testModel, Success! " + model.getAttribute("testModel");
    }

    /**
     * 测试接口幂等性
     *
     * @return
     */
    @ApiIdempotence
    @PostMapping("/idempotence")
    @ResponseBody
    public ResponseContent testIdempotence() {
        return ResponseUtil.success("测试接口幂等性成功！");
    }
}
