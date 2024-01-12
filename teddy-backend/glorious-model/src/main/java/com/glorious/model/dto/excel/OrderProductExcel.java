package com.glorious.model.dto.excel;

import cn.hutool.json.JSONUtil;
import com.glorious.model.dto.order.OrderDto;
import com.glorious.model.entity.base.Storehouse;
import com.glorious.model.entity.order.product.OrderProduct;
import com.glorious.model.entity.product.Product;
import com.glorious.model.entity.product.Variation;
import com.glorious.model.vo.product.ProductSimplyView;
import com.glorious.utils.utils.bean.QBeanUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductExcel {

    private Long product_sql_id;
    private String product_id;
    private String product_name;
    // 標示
    private String uid;
    // 連表
    private String variation_name;
    // 购买 数量
    private Integer quantity;
    // 单个产品的 总毛利率
    private BigDecimal total_profit;
    // 单个产品的 總金額
    private BigDecimal total_price;
    // 已 退貨 數目
    private Integer refunded_quantity;

    // private Long order_sql_id;
    // private Long variation_sql_id;
    // private ProductSimplyView product;
    // private String storehouse_name;
    // 平均价
    // private BigDecimal average_price;
    // 單價
    // private BigDecimal selling_price;
    // 记录 单品 優惠 已扣除 價格
    // private BigDecimal discount_deduction;


    public static OrderProductExcel byEntity(OrderProduct orderProduct) {
        Optional<OrderProductExcel> ops = Optional.ofNullable(QBeanUtil.convert(orderProduct, OrderProductExcel.class));
        ops.ifPresent(o -> {
            // if (orderDto.getStorehouse() != null) o.setStorehouse_name(orderDto.getStorehouse().getName());
            if (orderProduct.getVariation() != null) o.setVariation_name(orderProduct.getVariation().getName());
            o.setProduct_sql_id(orderProduct.getProduct_sql_id());
            o.setUid(
                    orderProduct.getProduct_sql_id() + "_" + orderProduct.getVariation_sql_id()
            );
        });
        return ops.orElse(null);
    }

    public static List<OrderProductExcel> byEntities(List<OrderDto> orderDtoList) {
        List<OrderProductExcel> res = new ArrayList<>();
        if (orderDtoList == null) return res;

        for (OrderDto od: orderDtoList) {
            if (StringUtils.hasLength(od.getOrdered_product())) {
                List<OrderProduct> orderProductList = JSONUtil.toList(od.getOrdered_product(), OrderProduct.class);

                for (OrderProduct op: orderProductList) {

                    OrderProductExcel one = OrderProductExcel.byEntity(op);
                    if (one != null) res.add(one);
                }
            }
        }

        return res;
    }
}
