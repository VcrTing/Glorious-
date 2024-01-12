package com.glorious.framework.component.sender;

import com.glorious.common.define.queue.RabbitNotifyConstants;
import com.glorious.model.entity.order.Order;
import com.glorious.model.entity.order.RefundedRecord;
import com.glorious.model.entity.restock.Invoice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificationQueueSender {

    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
    * 发出提醒 入货成功 队列
    */
    public void invoiceInsertSuccess(Invoice invoice) {
        rabbitTemplate.convertAndSend(
                RabbitNotifyConstants.EXCHANGE,
                RabbitNotifyConstants.KEY_INVOICE_INSERT_SUCCESS,
                invoice
                );
        log.debug("invoiceInsertSuccess 已发出");
    }

    /**
     * 发出提醒 下单 成功
     */
    public void checkoutSuccess(Order order) {
        rabbitTemplate.convertAndSend(
                RabbitNotifyConstants.EXCHANGE,
                RabbitNotifyConstants.KEY_CHECKOUT_SUCCESS,
                order
        );
        log.debug("checkoutSuccess 已发出");
    }

    /**
     * 发出提醒 下单 失败
     */
    public void checkoutError(String errorMsg) {
        rabbitTemplate.convertAndSend(
                RabbitNotifyConstants.EXCHANGE,
                RabbitNotifyConstants.KEY_CHECKOUT_ERROR,
                errorMsg
        );
        log.debug("checkoutError 已发出");
    }

    /**
     * 发出提醒 退单 成功
     */
    public void refundSuccess(RefundedRecord record) {
        rabbitTemplate.convertAndSend(
                RabbitNotifyConstants.EXCHANGE,
                RabbitNotifyConstants.KEY_REFUND_SUCCESS,
                record
        );
        log.debug("refundSuccess 已发出");
    }
}
