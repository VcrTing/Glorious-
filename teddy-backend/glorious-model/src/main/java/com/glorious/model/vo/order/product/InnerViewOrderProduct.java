package com.glorious.model.vo.order.product;

import com.glorious.model.entity.product.Product;
import com.glorious.model.entity.product.Variation;
import com.glorious.model.vo.product.ProductSimplyView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InnerViewOrderProduct {

    // 购买 数量
    private Integer quantity;
    // 平均价
    private BigDecimal average_price;
    // 單價
    private BigDecimal selling_price;

    private Variation variation;
    private Long variation_sql_id;

    private ProductSimplyView product;
    private Long product_sql_id;

    // 记录 单品 優惠 已扣除 價格
    private BigDecimal discount_deduction;

    // 单个产品的 总毛利率
    private BigDecimal profit;

    // 单个产品的 總金額
    private BigDecimal total_price;

    // 已 退貨 數目
    private Integer refunded_quantity;
}
