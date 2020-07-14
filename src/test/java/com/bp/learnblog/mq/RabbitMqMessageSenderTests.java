package com.bp.learnblog.mq;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * Mq 消息发送测试
 */
@Slf4j
@SpringBootTest
public class RabbitMqMessageSenderTests {

    @Resource
    private RabbitMqMessageSender mqMessageSender;

    @Test
    public void testDirectMessageSend() {
        Assertions.assertTrue(mqMessageSender.sendMessage("hey, RabbitMq的 第一条消息！"));
    }
}
