package com.glorious.queue;

import com.glorious.model.entity.product.Variation;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TestQueue {

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(name = "test"),
            value = @Queue(name = "test", durable = "false")
    ))
    public void test(Variation variation) {
        System.out.println("TEST MQ 传来的值 = " + variation );
    }
}
