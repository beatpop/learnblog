package com.bp.learnblog.controller.common;

import com.bp.learnblog.response.ResponseContent;
import com.bp.learnblog.service.common.IdempotenceTokenService;
import com.bp.learnblog.util.ResponseUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 接口幂等性Token获取接口
 *
 * @author DH
 */
@RequestMapping("/tokens")
@RestController
public class IdempotenceTokenController {
    @Resource
    private IdempotenceTokenService tokenService;

    @GetMapping("/requestid")
    public ResponseContent getToken() {
        return ResponseUtil.success(tokenService.createToken());
    }
}
