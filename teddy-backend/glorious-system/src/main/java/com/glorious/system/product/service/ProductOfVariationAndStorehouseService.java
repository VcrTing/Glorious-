package com.glorious.system.product.service;

import com.glorious.model.entity.base.Storehouse;
import com.glorious.model.entity.product.ProductOfVariationAndStorehouse;
import com.glorious.model.entity.product.Variation;

public interface ProductOfVariationAndStorehouseService {

    /**
    * 新增 样式，然后 连接到 产品
    */
    void connectionVariationForProduct(Long pid, Variation variation);
    /**
     * 新增 仓库，然后 连接到 产品
     */
    ProductOfVariationAndStorehouse connectionProductForStorehouse(Long pid, Long vid, Long sid);

    /**
    * one by pid, vid, sid
    */
    ProductOfVariationAndStorehouse fetch(Long pid, Long vid, Long sid);

    /**
    * 加货
    */
    Object insertQuantity(Long pid, Long vid, Long sid, Integer quantity);

    /**
    * 减货
    */
    Object removeQuantity(Long pid, Long vid, Long sid, Integer quantity);
}
