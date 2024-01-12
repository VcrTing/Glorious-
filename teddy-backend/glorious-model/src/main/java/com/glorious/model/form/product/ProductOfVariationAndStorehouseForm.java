package com.glorious.model.form.product;

import com.glorious.model.entity.product.ProductOfVariationAndStorehouse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductOfVariationAndStorehouseForm {

    private Integer quantity;

    private Long product_sql_id;
    private Long variation_sql_id;
    private Long storehouse_sql_id;

    public static ProductOfVariationAndStorehouse toEntity(Long pid, Long vid, Long sid) {
        ProductOfVariationAndStorehouse entity = new ProductOfVariationAndStorehouse();

        entity.setProduct_sql_id(pid);
        entity.setVariation_sql_id(vid);
        entity.setStorehouse_sql_id(sid);

        entity.setVersion(1);
        entity.setQuantity(0);

        return entity;
    }
}
