package com.glorious.common.define.datatset;

public interface RedisConstants {

    String KEY_HEAD = "glorious:cache:";

    String KEY_AUTH_USER = KEY_HEAD + "auth:user:id:";

    // 客戶等級
    String KEY_CUSTOMER_LEVEL = KEY_HEAD + "customer_level:id:";
    String KEY_CUSTOMER_LEVEL_LIST = KEY_HEAD + "customer_level:list";

    // 倉庫
    String KEY_STOREHOUSE = KEY_HEAD + "storehouse:id:";
    String KEY_STOREHOUSE_LIST = KEY_HEAD + "storehouse:list";

    // 供應商
    String KEY_SUPPLIER = KEY_HEAD + "supplier:id:";
    String KEY_SUPPLIER_LIST = KEY_HEAD + "supplier:list";

    // 標籤
    String KEY_LABEL = KEY_HEAD + "label:id:";
    String KEY_LABEL_LIST = KEY_HEAD + "label:list";
    // 樣式
    String KEY_VARIATION = KEY_HEAD + "variation:id:";
    String KEY_VARIATION_LIST = KEY_HEAD + "variation:list";
    // 產品
    String KEY_PRODUCT = KEY_HEAD + "product:id:";
    String KEY_PRODUCT_LIST = KEY_HEAD + "product:list";

    // 用户
    String KEY_USER = KEY_HEAD + "user:id:";
    String KEY_USER_LIST = KEY_HEAD + "user:list";

    // 入貨
    String KEY_INVOICE = KEY_HEAD + "invoice:id:";
    String KEY_INVOICE_LIST = KEY_HEAD + "invoice:list";
}
