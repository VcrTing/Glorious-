package com.glorious.model.vo.product.label;

import com.glorious.model.entity.product.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InnerViewLabelProducts {

    private Long id;
    private String product_id;
    private String name;
    private String product_name;

    public static InnerViewLabelProducts byEntity(Product product) {
        InnerViewLabelProducts ivp = new InnerViewLabelProducts();
        if (product != null) {
            ivp.setId(product.getId());
            ivp.setName(product.getName());
            ivp.setProduct_name(product.getName());
            ivp.setProduct_id(product.getProduct_id());
        }
        return ivp;
    }
}
