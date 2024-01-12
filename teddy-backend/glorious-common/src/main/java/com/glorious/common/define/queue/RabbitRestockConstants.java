package com.glorious.common.define.queue;

public interface RabbitRestockConstants {

    // 交换机
    String EXCHANGE = "restock";

    // 新增入货表
    String RESTOCK = EXCHANGE + ".queue.restock";
    String KEY_INSERT = "insert";

    // 修改产品入货价格
    String PRODUCT_1 = EXCHANGE + ".queue.product.1";
    String KEY_UPDATE_AVERAGE_PRICE = "update.average.price";
}
