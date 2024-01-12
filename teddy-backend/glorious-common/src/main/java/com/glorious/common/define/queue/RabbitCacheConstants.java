package com.glorious.common.define.queue;

public interface RabbitCacheConstants {
    String EXCHANGE = "cache";

    String KEY_USER = "user";
    String KEY_LABEL = "label";
    String KEY_SUPPLIER = "supplier";
    String KEY_STOREHOUSE = "storehouse";
    String KEY_CUSTOMER_LEVEL = "customer_level";

    String REFRESH_LIST_1 = EXCHANGE + ".queue.refresh.list.1";
    String REFRESH_LIST_2 = EXCHANGE + ".queue.refresh.list.2";
}
