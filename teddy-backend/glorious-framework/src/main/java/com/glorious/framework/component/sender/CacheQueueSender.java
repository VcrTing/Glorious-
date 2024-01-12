package com.glorious.framework.component.sender;

import com.glorious.common.define.queue.RabbitCacheConstants;
import com.glorious.common.define.queue.RabbitNotifyConstants;
import com.glorious.model.entity.product.Variation;
import com.glorious.model.entity.restock.Invoice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CacheQueueSender {

    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * 发出提醒 刷新
     */
    public void refreshLabelList() {
        rabbitTemplate.convertAndSend( RabbitCacheConstants.EXCHANGE, RabbitCacheConstants.KEY_LABEL );
        log.debug("refreshLabelList 已发出");
    }
    /**
     * 发出提醒 刷新
     */
    public void refreshSupplierList() {
        rabbitTemplate.convertAndSend( RabbitCacheConstants.EXCHANGE, RabbitCacheConstants.KEY_SUPPLIER );
        log.debug("refreshSupplierList 已发出");
    }
    /**
     * 发出提醒 刷新
     */
    public void refreshStorehouseList() {
        rabbitTemplate.convertAndSend( RabbitCacheConstants.EXCHANGE, RabbitCacheConstants.KEY_STOREHOUSE );
        log.debug("refreshStorehouseList 已发出");
    }
    /**
     * 发出提醒 刷新
     */
    public void refreshCustomerLevelList() {
        rabbitTemplate.convertAndSend( RabbitCacheConstants.EXCHANGE, RabbitCacheConstants.KEY_CUSTOMER_LEVEL );
        log.debug("refreshCustomerLevelList 已发出");
    }
}
