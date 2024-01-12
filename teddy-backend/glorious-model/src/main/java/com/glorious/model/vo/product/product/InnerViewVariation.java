package com.glorious.model.vo.product.product;

import com.glorious.model.entity.base.Storehouse;
import com.glorious.model.entity.product.Variation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InnerViewVariation {

    private Long id;
    private String name;
    private Integer quantity;

    private Storehouse storehouse;

    public static InnerViewVariation byEntity(Variation variation, Storehouse storehouse) {
        log.debug("InnerViewVariation byEntity variation = {}", variation);
        log.debug("InnerViewVariation byEntity storehouse = {}", storehouse);
        if (variation == null || storehouse == null) return null;
        return new InnerViewVariation(
            variation.getId(), variation.getName(), 0, storehouse
        );
    }
    public static InnerViewVariation byInventory(Variation variation, Storehouse storehouse, Integer quantity) {
        log.debug("InnerViewVariation byEntity variation = {}", variation);
        log.debug("InnerViewVariation byEntity storehouse = {}", storehouse);
        if (variation == null || storehouse == null) return null;
        return new InnerViewVariation(
                variation.getId(), variation.getName(), quantity, storehouse
        );
    }
}
