package com.glorious.model.form.order.refund;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InnerFormRefundInfo {

    private Long product;
    private Long variation;

    private Integer refunded_quantity;
    private BigDecimal refunded_price;
}
