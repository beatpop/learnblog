package com.bp.learnblog.controller.common;

import com.bp.learnblog.mq.RabbitMqMessageSender;
import com.bp.learnblog.response.ResponseContent;
import com.bp.learnblog.util.ResponseUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * RabbitMq 测试
 *
 * @author DH
 */
@RequestMapping("/rabbitmq")
@RestController
public class RabbitMqController {

    @Resource
    private RabbitMqMessageSender mqMessageSender;

    /**
     * 发送RabbitMq消息
     *
     * @param message
     * @return
     */
    @GetMapping("/send")
    public ResponseContent sendMessage(@RequestParam("message") String message) {
        mqMessageSender.sendMessage(message);
        return ResponseUtil.success("Mq消息发送成功！");
    }
}
