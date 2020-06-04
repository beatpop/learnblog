package com.bp.learnblog.controller;

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
//        log.info("server.port: {}",environment.getProperty("server.port"));
        return "test";
    }

//    @ModelAttribute
//    public void testModelAttribute(@PathVariable("mod") String mod, Model model) {
//        if (mod.isEmpty()) {
//            mod = "null";
//        }
//        model.addAttribute("testModel", mod);
//    }

    @GetMapping("/{mod}/attribute")
    @ResponseBody
    public String testModelAttribute(@PathVariable("mod") String mod, Model model) {
        model.addAttribute("testModel", mod);
        System.out.println(model.containsAttribute("testModel"));
        return "testModel, Success! " + model.getAttribute("testModel");
    }
}
