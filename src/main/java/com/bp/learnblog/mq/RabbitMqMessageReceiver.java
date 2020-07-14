package com.bp.learnblog.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 消息消费者
 *
 * @author DH
 */
@Slf4j
@Component
@RabbitListener(queues = "testMqQueue")
public class RabbitMqMessageReceiver {

    @RabbitHandler
    public void directMessageReceive(Map<String, Object> message) {
        log.info("Direct消费者接收到消息: {}", message.toString());
    }
}
