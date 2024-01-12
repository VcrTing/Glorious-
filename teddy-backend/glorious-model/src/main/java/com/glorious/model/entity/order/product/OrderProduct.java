package com.glorious.model.entity.order.product;

import com.glorious.model.entity.product.Variation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderProduct {

    private Long product_sql_id;
    private Long variation_sql_id;
    private Variation variation;

    // 平均价
    private BigDecimal average_price;
    // 單價
    private BigDecimal selling_price;
    // 购买 数量
    private Integer quantity;

    // 记录 单品 優惠 已扣除 價格
    private BigDecimal discount_deduction;
    // 单个产品的 总毛利率
    private BigDecimal total_profit;
    // 单个产品的 總金額
    private BigDecimal total_price;
    // 已 退貨 數目
    private Integer refunded_quantity;
}
