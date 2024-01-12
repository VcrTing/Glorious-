package com.glorious.framework.conf;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConf {
/*
    @Bean
    public MessageConverter jsonConverter() {

        Jackson2JsonMessageConverter jjmc = new Jackson2JsonMessageConverter();

        // 用 唯一 ID 确保 消息 不会 重复 提交
        jjmc.setCreateMessageIds(true);

        return jjmc;
    }


 */
}
