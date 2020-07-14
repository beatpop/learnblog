package com.bp.learnblog.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMq 配置
 *
 * @author DH
 */
@Configuration
public class RabbitMqConfig {

    /**
     * 创建队列名为 testMqQueue
     *
     * @return
     */
    @Bean
    public Queue testMqQueue() {
        /**
         * Queue的参数说明：
         * durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
         * exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
         * autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
         */
        return new Queue("testMqQueue", true);
    }

    /**
     * 创建直连交换机 名为 testMqDirectExchange
     * @return
     */
    @Bean
    public DirectExchange testMqDirectExchange() {
        return new DirectExchange("testMqDirectExchange", true, false);
    }

    /**
     * 绑定: 将队列和交换机绑定, 并设置用于匹配键：testMqDirectRouting
     *
     * @return
     */
    @Bean
    public Binding bindingMqDirect() {
        return BindingBuilder.bind(testMqQueue()).to(testMqDirectExchange()).with("testMqDirectRouting");
    }

    /**
     * 设置消息转换格式
     *
     * @return
     */
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
