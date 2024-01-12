package com.glorious.model.dto.product;

import com.glorious.model.entity.BaseEntity;
import com.glorious.model.entity.base.Storehouse;
import com.glorious.model.entity.product.Product;
import com.glorious.model.entity.product.Variation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductOfVariationAndStorehouseDto extends BaseEntity {

    private Integer quantity;

    private Product product;
    private Variation variation;
    private Storehouse storehouse;

    private Long product_sql_id;
    private Long variation_sql_id;
    private Long storehouse_sql_id;

    public static List<ProductOfVariationAndStorehouseDto> filterByProduct(List<ProductOfVariationAndStorehouseDto> list, Long productID) {
        return list.stream().filter(l -> l.getProduct_sql_id().equals(productID)).collect(Collectors.toList());
    }
}
