package com.glorious.system.product.service.implExtra;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.glorious.model.entity.product.ProductOfVariationAndStorehouse;
import com.glorious.model.mapper.base.StorehouseMapper;
import com.glorious.model.mapper.product.ProductMapper;
import com.glorious.model.mapper.product.ProductOfVariationAndStorehouseMapper;
import com.glorious.model.mapper.product.VariationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductOfVariationAndStorehouseServiceImplExtra extends ServiceImpl<ProductOfVariationAndStorehouseMapper, ProductOfVariationAndStorehouse> {

    @Autowired
    ProductOfVariationAndStorehouseMapper mapper;

    /**
    * 深度 分页列表
    */
    public List<ProductOfVariationAndStorehouse> multiDeep() {
        return mapper.multiDeep(null);
    }

    /**
     * 修改 库存
     */
    public Boolean updQuantity(ProductOfVariationAndStorehouse entity, Integer quantity) {
        return this.lambdaUpdate()
                .eq(ProductOfVariationAndStorehouse::getId, entity.getId())
                .set(ProductOfVariationAndStorehouse::getQuantity, quantity)
                .update();
    }
}
