package com.glorious.model.dto.excel;

import com.glorious.model.dto.product.ProductOfVariationAndStorehouseDto;
import com.glorious.model.entity.base.Storehouse;
import com.glorious.model.entity.product.Product;
import com.glorious.model.entity.product.Variation;
import com.glorious.utils.utils.bean.QBeanUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInventoryExcel {
    private Integer quantity;
    private Product product;
    private Variation variation;
    private Storehouse storehouse;

    public static ProductInventoryExcel byDto(ProductOfVariationAndStorehouseDto dto) {
        Optional<ProductInventoryExcel> ops = Optional.ofNullable(QBeanUtil.convert(dto, ProductInventoryExcel.class));
        ops.ifPresent(o -> {
            if (o.getProduct() == null) o.setProduct(new Product());
            if (o.getVariation() == null) o.setVariation(new Variation());
            if (o.getStorehouse() == null) o.setStorehouse(new Storehouse());
        });
        return ops.orElse(null);
    }
    public static List<ProductInventoryExcel> byDtoList(List<ProductOfVariationAndStorehouseDto> dtoList) {
        return dtoList.stream()
                .map(ProductInventoryExcel::byDto)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
