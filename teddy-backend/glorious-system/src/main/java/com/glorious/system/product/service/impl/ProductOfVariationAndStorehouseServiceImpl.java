package com.glorious.system.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.glorious.framework.module.dataset.BackendCacheService;
import com.glorious.model.entity.base.Storehouse;
import com.glorious.model.entity.product.Product;
import com.glorious.model.entity.product.ProductOfVariationAndStorehouse;
import com.glorious.model.entity.product.Variation;
import com.glorious.model.form.product.ProductOfVariationAndStorehouseForm;
import com.glorious.model.mapper.base.StorehouseMapper;
import com.glorious.model.mapper.product.ProductMapper;
import com.glorious.model.mapper.product.ProductOfVariationAndStorehouseMapper;
import com.glorious.model.mapper.product.VariationMapper;
import com.glorious.system.product.service.ProductOfVariationAndStorehouseService;
import com.glorious.system.product.service.implExtra.ProductOfVariationAndStorehouseServiceImplExtra;
import com.glorious.utils.utils.basic.QSumUtil;
import com.glorious.utils.utils.basic.QTypeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductOfVariationAndStorehouseServiceImpl extends ServiceImpl<ProductOfVariationAndStorehouseMapper, ProductOfVariationAndStorehouse> implements ProductOfVariationAndStorehouseService {

    @Autowired
    ProductMapper productMapper;

    @Autowired
    VariationMapper variationMapper;

    @Autowired
    StorehouseMapper storehouseMapper;

    @Autowired
    ProductOfVariationAndStorehouseMapper mapper;

    @Autowired
    ProductOfVariationAndStorehouseServiceImplExtra serviceImplExtra;

    @Autowired
    BackendCacheService backendCacheService;

    /**
    * 为产品 连接 样式库存
    */
    @Override
    public void connectionVariationForProduct(Long pid, Variation variation) {
        if (variationMapper.insert(variation) > 0) {
            List<Storehouse> storehouses = backendCacheService.getStorehouseList(); // storehouseMapper.selectList(null);
            for (Storehouse s: storehouses) {
                // 每一个仓库，均新增一个 库存 数据
                mapper.insert(ProductOfVariationAndStorehouseForm.toEntity(pid, variation.getId(), s.getId()));
            }
        }
    }
    /**
     * 新增 仓库，然后 连接到 产品
     */
    @Override
    public ProductOfVariationAndStorehouse connectionProductForStorehouse(Long pid, Long vid, Long sid) {
        ProductOfVariationAndStorehouse entity = new ProductOfVariationAndStorehouse();
        entity.setVersion(1);
        entity.setQuantity(0);
        entity.setProduct_sql_id(pid);
        entity.setVariation_sql_id(vid);
        entity.setStorehouse_sql_id(sid);
        return this.save(entity) ? entity : null;
    }

    /**
    * 获取 一个
    */
    @Override
    public ProductOfVariationAndStorehouse fetch(Long pid, Long vid, Long sid) {
        return this.lambdaQuery()
                .eq(ProductOfVariationAndStorehouse::getProduct_sql_id, pid)
                .eq(ProductOfVariationAndStorehouse::getVariation_sql_id, vid)
                .eq(ProductOfVariationAndStorehouse::getStorehouse_sql_id, sid)
                .one();
    }

    /**
    * 新增 库存
    */
    @Override
    public Object insertQuantity(Long pid, Long vid, Long sid, Integer quantity) {
        if (pid == null || vid == null || sid == null) return "新增庫存時，產品、樣式、倉庫之間某項 ID 異常";
        ProductOfVariationAndStorehouse entity = fetch(pid, vid, sid);
        // 没找到 库存信息 就增加库存
        if (entity == null) {
            entity = connectionProductForStorehouse(pid, vid, sid);
            if (entity == null) return "因網絡波動，為產品連接新的樣式庫存數據失敗";
        }
        Integer res = QSumUtil.add(entity.getQuantity(), quantity);
        return serviceImplExtra.updQuantity(entity, res) ? true : "因網絡波動，產品庫存入庫失敗";
    }

    /**
    * 移除 库存
    */
    @Override
    public Object removeQuantity(Long pid, Long vid, Long sid, Integer quantity) {
        if (pid == null || vid == null || sid == null) return "移除庫存時，產品、樣式、倉庫之間某項 ID 異常";
        ProductOfVariationAndStorehouse entity = fetch(pid, vid, sid);
        if (entity == null) return "產品倉庫數據未找到，請重識";
        Integer res = QSumUtil.sub(entity.getQuantity(), quantity);
        if (res < 0) return "產品的庫存數據小於要減去的倉庫內庫存數據";
        return serviceImplExtra.updQuantity(entity, res) ? true : "產品移除庫存失敗";
    }
}
