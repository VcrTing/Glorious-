package com.glorious.model.vo.order.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InnerViewPaymentMethod {

    private String name;
    private BigDecimal price;
}
