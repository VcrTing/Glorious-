package com.glorious.model.dto.order;

import com.glorious.model.define.enums.EnumOrderStatus;
import com.glorious.model.entity.base.Storehouse;
import com.glorious.model.entity.custom.Customer;
import com.glorious.model.entity.custom.CustomerLevel;
import com.glorious.model.entity.sys.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long id;
    private String order_id;
    private Date order_date;
    private EnumOrderStatus status;
    private BigDecimal total_price;

    // 聯表
    private Storehouse storehouse;
    private User cashier;
    private Customer customer;
    private CustomerLevel customer_level;

    // JSON 装 ARRAY
    private String discount;
    // JSON 装 ARRAY
    private String payment_method;
    // JSON 裝 產品 購買 信息
    private String ordered_product;
    // 退貨 備註
    private String refunded_remarks;

    // 退款
    // JSON 裝 退款之后的 产品：OrderProduct
    private String ordered_product_after_refund;
    // 退款后的 订单金额
    private BigDecimal total_price_after_refund;
    // 退款后的 毛利率
    private BigDecimal total_profit_after_refund;
}
