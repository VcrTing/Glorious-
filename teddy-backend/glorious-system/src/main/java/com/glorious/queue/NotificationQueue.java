package com.glorious.queue;

import com.glorious.common.define.queue.RabbitNotifyConstants;
import com.glorious.framework.module.notification.CustomerNotificationService;
import com.glorious.framework.module.notification.SysNotificationService;
import com.glorious.model.entity.order.Order;
import com.glorious.model.entity.order.RefundedRecord;
import com.glorious.model.entity.restock.Invoice;
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
public class NotificationQueue {

    @Autowired
    SysNotificationService notificationService;

    @Autowired
    CustomerNotificationService customerNotificationService;

    /**
    * 入货成功
    */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(name = RabbitNotifyConstants.EXCHANGE, type = ExchangeTypes.FANOUT),
            value = @Queue(name = RabbitNotifyConstants.INVOICE, durable = "true"),
            key = RabbitNotifyConstants.KEY_INVOICE_INSERT_SUCCESS
    ))
    public void notifyInvoiceSuccess(Invoice invoice) {
        log.debug("notifyInvoiceSuccess 收到 = " + invoice);
        notificationService.notifyInvoiceInsert(invoice);
    }

    /**
    * 下单成功
    */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(name = RabbitNotifyConstants.EXCHANGE, type = ExchangeTypes.FANOUT),
            value = @Queue(name = RabbitNotifyConstants.CHECKOUT, durable = "true"),
            key = RabbitNotifyConstants.KEY_CHECKOUT_SUCCESS
    ))
    public void notifyCheckoutSuccess(Order order) {
        log.debug("notifyCheckoutSuccess 收到 = " + order);
        customerNotificationService.notifyCheckoutSuccess(order);
    }

    /**
     * 下单失败
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(name = RabbitNotifyConstants.EXCHANGE, type = ExchangeTypes.FANOUT),
            value = @Queue(name = RabbitNotifyConstants.CHECKOUT, durable = "true"),
            key = RabbitNotifyConstants.KEY_CHECKOUT_ERROR
    ))
    public void notifyCheckoutError(String errorMsg) {
        log.debug("notifyCheckoutError 收到 = " + errorMsg);
        customerNotificationService.notifyCheckoutError(errorMsg);
    }

    /**
     * 退单成功
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(name = RabbitNotifyConstants.EXCHANGE, type = ExchangeTypes.FANOUT),
            value = @Queue(name = RabbitNotifyConstants.REFUND, durable = "true"),
            key = RabbitNotifyConstants.KEY_REFUND_SUCCESS
    ))
    public void notifyRefundSuccess(Order order, RefundedRecord record) {
        log.debug("notifyRefundSuccess 收到 = " + order);
        customerNotificationService.notifyRefundSuccess(order, record);
    }
}
