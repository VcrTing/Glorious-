package com.glorious.model.entity.order;

import com.baomidou.mybatisplus.annotation.TableName;
import com.glorious.model.define.enums.EnumOrderStatus;
import com.glorious.model.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@TableName("odr_order")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseEntity {

    private String order_id;
    private Date order_date;
    private EnumOrderStatus status;

    // 下单时的 订单金额
    private BigDecimal total_price;

    // 下单时的 毛利率
    private BigDecimal total_profit;

    // 聯表
    private Long storehouse_sql_id;
    private Long cashier_sql_id;
    private Long customer_sql_id;
    private Long customer_level_sql_id;

    // JSON 装 ARRAY
    private String discount;

    // JSON 装 ARRAY
    private String payment_method;

    // JSON 裝 產品 購買 信息：OrderProduct
    private String ordered_product;

    // 退貨 備註
    private String refunded_remarks;

    // JSON 裝 退款之后的 产品：OrderProduct
    private String ordered_product_after_refund;
    // 退款后的 订单金额
    private BigDecimal total_price_after_refund;
    // 退款后的 毛利率
    private BigDecimal total_profit_after_refund;

    private Integer version;
}
