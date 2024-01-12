package com.glorious.model.vo.product.product.inner;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InnerViewRestockDistribute {

    private Long storehouse;
    private Long variation;
    private Integer quantity;
}
