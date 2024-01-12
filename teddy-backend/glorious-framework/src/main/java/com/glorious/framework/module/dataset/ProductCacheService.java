package com.glorious.framework.module.dataset;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.glorious.common.define.datatset.RedisConstants;
import com.glorious.framework.component.tools.RedisTool;
import com.glorious.model.entity.base.Storehouse;
import com.glorious.model.entity.base.Supplier;
import com.glorious.model.entity.custom.CustomerLevel;
import com.glorious.model.entity.product.Label;
import com.glorious.model.entity.product.Product;
import com.glorious.model.entity.product.Variation;
import com.glorious.model.mapper.base.StorehouseMapper;
import com.glorious.model.mapper.custom.CustomerLevelMapper;
import com.glorious.model.mapper.product.LabelMapper;
import com.glorious.model.mapper.product.ProductMapper;
import com.glorious.model.mapper.product.VariationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductCacheService {

    @Autowired
    RedisTool redisTool;

    @Autowired
    LabelMapper labelMapper;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    VariationMapper variationMapper;

    // 產品其他數據 存活時間是 7 天
    final static int LIVE_MINUTE = 24 * 60 * 7;
    // 產品數據 存活時間是 2 天
    final static int PRODUCT_LIVE_MINUTE = 24 * 60 * 2;

    /**
     * 獲取 標籤，沒有就從數據庫裡面取
     */
    public List<Label> getLabelList() {
        List<Label> res = getLabelListCache();
        return (res != null && !res.isEmpty()) ? res : refreshLabelList();
    }
    public List<Label> getLabelListCache() {
        return redisTool.getCacheList(RedisConstants.KEY_LABEL_LIST);
    }
    /**
     * ID 獲取
     */
    public Label getLabel(Long id) {
        Label res = redisTool.getObject(RedisConstants.KEY_LABEL + id);
        return res != null ? res : labelMapper.selectById(id);
    }
    /**
     * 刷新 標籤
     */
    public List<Label> refreshLabelList() {
        LambdaQueryWrapper<Label> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderBy(true, false, Label::getId);
        List<Label> res = labelMapper.selectList(queryWrapper);
        setLabelList(res);
        return res;
    }
    /**
     * 設置 標籤
     */
    public void setLabelList(List<Label> src) {
        if (src != null && !src.isEmpty()) {
            redisTool.setCacheList(RedisConstants.KEY_LABEL_LIST, src, redisTool.randomMinute(LIVE_MINUTE));
            for (Label s: src) {
                redisTool.setObject(RedisConstants.KEY_LABEL + s.getId(), s, redisTool.randomMinute(LIVE_MINUTE));
            }
        }
    }


    /**
     * 获取 单个产品，该产品数据大多是 静态数据
     */
    public Product getProduct(Long id) {
        Product res = redisTool.getObject(RedisConstants.KEY_PRODUCT + id);
        if (res == null) {
            res = productMapper.selectById(id);
            setProduct(res);
        }
        return res;
    }
    /**
     * 设置 单个产品
     */
    public void setProduct(Product product) {
        redisTool.setObject(RedisConstants.KEY_PRODUCT + product.getId(), product, redisTool.randomMinute(PRODUCT_LIVE_MINUTE));
    }

    /**
     * 获取 单个樣式，该产品数据大多是 静态数据
     */
    public Variation getVariation(Long id) {
        Variation res = redisTool.getObject(RedisConstants.KEY_VARIATION + id);
        if (res == null) {
            res = variationMapper.selectById(id);
            setVariation(res);
        }
        return res;
    }
    /**
     * 设置 单个樣式
     */
    public void setVariation(Variation variation) {
        redisTool.setObject(RedisConstants.KEY_VARIATION + variation.getId(), variation, redisTool.randomMinute(LIVE_MINUTE));
    }
}
