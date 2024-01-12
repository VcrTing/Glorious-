package com.glorious.framework.component.sender;

import com.glorious.model.entity.product.Variation;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BackendQueueSender {

    @Autowired
    RabbitTemplate rabbitTemplate;

    // 测试
    public void test() {
        Variation variation = new Variation();
        variation.setName("AAAA");
        rabbitTemplate.convertAndSend("test", variation);
        System.out.println("发送方 = " + variation);
    }
}
