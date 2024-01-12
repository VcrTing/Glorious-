package com.glorious.system.order.service.implVIew;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.glorious.common.mvc.AjaxResult;
import com.glorious.model.dto.order.OrderDto;
import com.glorious.model.entity.order.Order;
import com.glorious.model.entity.product.Product;
import com.glorious.model.entity.product.Variation;
import com.glorious.model.mapper.product.ProductMapper;
import com.glorious.model.mapper.product.VariationMapper;
import com.glorious.model.mapperMulti.order.OrderDtoMapper;
import com.glorious.model.vo.order.OrderDetailView;
import com.glorious.model.vo.order.product.InnerViewOrderProduct;
import com.glorious.model.vo.product.ProductSimplyView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderServiceImplView {

    @Autowired
    OrderDtoMapper dtoMapper;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    VariationMapper variationMapper;

    /**
    * 订单详情
    */
    public AjaxResult detail(QueryWrapper<Order> queryWrapper) {
        List<OrderDto> dtoList = dtoMapper.multiDeep(queryWrapper);
        if (dtoList.size() <= 0) AjaxResult.error("未找到任何订单数据");
        OrderDetailView result = OrderDetailView.byDTO(dtoList.get(0));
        if (result == null) return AjaxResult.error("订单详情数据为空");

        // 获取 产品订单数据
        List<InnerViewOrderProduct> orderProducts = result.getOrdered_product();
        orderProducts.forEach(p -> {
            Product product = productMapper.selectById(p.getProduct_sql_id());
            p.setProduct(ProductSimplyView.byEntity(product));
            Variation variation = variationMapper.selectById(p.getVariation_sql_id());
            p.setVariation(variation);
        });

        // 返回
        return AjaxResult.restfull(result.getId() != null, result);
    }
}
