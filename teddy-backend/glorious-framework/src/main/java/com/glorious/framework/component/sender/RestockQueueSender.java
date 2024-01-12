package com.glorious.framework.component.sender;

import com.glorious.common.define.queue.RabbitCacheConstants;
import com.glorious.common.define.queue.RabbitNotifyConstants;
import com.glorious.common.define.queue.RabbitRestockConstants;
import com.glorious.model.entity.restock.Invoice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RestockQueueSender {

    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * 計算 平均入貨價格
     */
    public void averageRestockPrice() {
        rabbitTemplate.convertAndSend( RabbitRestockConstants.EXCHANGE, RabbitRestockConstants.KEY_UPDATE_AVERAGE_PRICE );
        log.debug("averageRestockPrice 已发出");
    }
}
