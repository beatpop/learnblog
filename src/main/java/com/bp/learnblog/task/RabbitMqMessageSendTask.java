package com.bp.learnblog.task;

import com.bp.learnblog.mq.RabbitMqMessageSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author DH
 */
@Slf4j
@Component
public class RabbitMqMessageSendTask {

    private static int mesNum = 0;

    @Resource
    private RabbitMqMessageSender mqMessageSender;

    /**
     * 每分钟执行一次
     */
    @Scheduled(cron = "* 1 * * * ?")
    public void messageSend() {
        log.info("定时发送消息任务-{}", mesNum);
        mqMessageSender.sendMessage("定时发送MQ消息任务-" + mesNum++);
    }
}
