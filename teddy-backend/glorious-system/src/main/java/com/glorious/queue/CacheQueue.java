package com.glorious.queue;

import com.glorious.common.define.queue.RabbitCacheConstants;
import com.glorious.framework.module.dataset.BackendCacheService;
import com.glorious.framework.module.dataset.ProductCacheService;
import com.glorious.framework.module.dataset.UserCacheService;
import com.glorious.model.entity.product.Variation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CacheQueue {

    @Autowired
    UserCacheService userCacheService;

    @Autowired
    ProductCacheService productCacheService;

    @Autowired
    BackendCacheService backendCacheService;


    /**
     * 刷新 标签
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(name = RabbitCacheConstants.EXCHANGE, type = ExchangeTypes.FANOUT),
            value = @Queue(name = RabbitCacheConstants.REFRESH_LIST_1, durable = "true"),
            key = RabbitCacheConstants.KEY_LABEL
    ))
    public void refreshLabelList() { log.debug("refreshLabelList 收到"); productCacheService.refreshLabelList(); }

    /**
     * 刷新 供应商
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(name = RabbitCacheConstants.EXCHANGE, type = ExchangeTypes.FANOUT),
            value = @Queue(name = RabbitCacheConstants.REFRESH_LIST_2, durable = "true"),
            key = RabbitCacheConstants.KEY_SUPPLIER
    ))
    public void refreshSupplierList() { log.debug("refreshSupplierList 收到"); backendCacheService.refreshSupplierList(); }

    /**
     * 刷新 仓库
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(name = RabbitCacheConstants.EXCHANGE, type = ExchangeTypes.FANOUT),
            value = @Queue(name = RabbitCacheConstants.REFRESH_LIST_1, durable = "true"),
            key = RabbitCacheConstants.KEY_STOREHOUSE
    ))
    public void refreshStorehouseList() { log.debug("refreshStorehouseList 收到"); backendCacheService.refreshStorehouseList(); }

    /**
     * 刷新 会员等级
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(name = RabbitCacheConstants.EXCHANGE, type = ExchangeTypes.FANOUT),
            value = @Queue(name = RabbitCacheConstants.REFRESH_LIST_2, durable = "true"),
            key = RabbitCacheConstants.KEY_CUSTOMER_LEVEL
    ))
    public void refreshCustomerLevelList() { log.debug("refreshCustomerLevelList 收到"); backendCacheService.refreshCustomerLevelList(); }

}
