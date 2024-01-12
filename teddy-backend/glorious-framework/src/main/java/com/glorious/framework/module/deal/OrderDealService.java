package com.glorious.framework.module.deal;

import com.glorious.common.exception.QLogicException;
import com.glorious.common.mvc.AjaxResult;
import com.glorious.framework.module.notification.CustomerNotificationService;
import com.glorious.framework.component.sender.NotificationQueueSender;
import com.glorious.model.entity.custom.Customer;
import com.glorious.model.entity.order.Order;
import com.glorious.model.entity.order.RefundedRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class OrderDealService {

    @Autowired
    CustomerNotificationService customerNotificationService;

    @Autowired
    NotificationQueueSender notificationQueueSender;

    /**
    * 訂單成功
    */
    public AjaxResult dealCheckoutSuccess(Order order, Customer customer) {
        if (customer != null && StringUtils.hasText(customer.getEmail()))
            notificationQueueSender.checkoutSuccess(order); // customerNotificationService.notifyCheckoutSuccess(order);
        return AjaxResult.success(order);
    }

    /**
     * 訂單失敗
     */
    public void dealCheckoutError(Object result, Customer customer) {
        if (result instanceof String) {
            if (customer != null && StringUtils.hasText(customer.getEmail()))
                notificationQueueSender.checkoutError(result.toString()); // customerNotificationService.notifyCheckoutError(result.toString());
            throw new QLogicException(result.toString());
        }
    }

    /**
    * 訂單退單
    */
    public void dealRefund(Object result, Order order, RefundedRecord record, Customer customer) {
        if (result instanceof String) {
            if (customer != null && StringUtils.hasText(customer.getEmail()))
                notificationQueueSender.refundSuccess(record); // customerNotificationService.notifyRefundSuccess(order, record);
            throw new QLogicException(result.toString());
        }
    }
}
