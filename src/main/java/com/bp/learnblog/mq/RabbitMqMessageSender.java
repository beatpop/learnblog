package com.bp.learnblog.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 消息生产者
 *
 * @author DH
 */
@Slf4j
@Component
public class RabbitMqMessageSender {

    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 队列消息发送
     *
     * @param object
     * @return
     */
    public boolean sendMessage(Object object) {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = object.toString();
        String createdTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, String> map = new ConcurrentHashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createdTime", createdTime);
        try {
            rabbitTemplate.convertAndSend("testMqDirectExchange", "testMqDirectRouting", map);
            log.info("RabbitMq 发送消息成功： {}", map);
            return true;
        } catch (Exception e) {
            log.warn("RabbitMq发送消息失败： {}", map);
            log.warn("失败原因： {}", e.toString());
            return false;
        }
    }
}
