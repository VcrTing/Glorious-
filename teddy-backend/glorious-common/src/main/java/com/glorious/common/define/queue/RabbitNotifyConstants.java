package com.glorious.common.define.queue;

public interface RabbitNotifyConstants {

    String EXCHANGE = "notification";


    // 发票入货
    String INVOICE = EXCHANGE + ".queue.invoice";
    String KEY_INVOICE_INSERT_SUCCESS = "invoice.insert.success";

    // 用户下单
    String CHECKOUT = EXCHANGE + ".queue.checkout";
    String KEY_CHECKOUT_SUCCESS = "checkout.success";
    String KEY_CHECKOUT_ERROR = "checkout.error";

    // 用户退单
    String REFUND = EXCHANGE + ".queue.refund";
    String KEY_REFUND_SUCCESS = "refund.success";
}
