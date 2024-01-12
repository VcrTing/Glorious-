package com.glorious.queue;

import com.glorious.common.define.queue.RabbitRestockConstants;
import com.glorious.model.entity.restock.Invoice;
import com.glorious.model.mapper.restock.InvoiceMapper;
import com.glorious.system.product.service.implExtra.ProductServiceImplExtra;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class RestockQueue {

    @Autowired
    ProductServiceImplExtra serviceImplExtra;

    @Autowired
    InvoiceMapper invoiceMapper;

    /**
    * 新增 RESTOCK 表数据
    */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(name = "test"),
            value = @Queue(name = "test", durable = "false")
    ))
    public void doing() {

    }

    /**
    * 更新 PRODUCT 平均 入货价格
    */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(name = RabbitRestockConstants.EXCHANGE, type = ExchangeTypes.FANOUT),
            value = @Queue(name = RabbitRestockConstants.PRODUCT_1, durable = "true"),
            key = RabbitRestockConstants.KEY_UPDATE_AVERAGE_PRICE
    ))
    public void averageRestockPrice(Long invoiceID, Long productID, BigDecimal nowRestockPrice) {
        // 較嚴 發票是否已經回滾
        Invoice invoice = invoiceMapper.selectById(invoiceID);
        if (invoice != null) serviceImplExtra.updAverageRestockPrice(productID, nowRestockPrice);
    }
}
