package com.glorious.model.vo.product;

import com.glorious.model.entity.product.Label;
import com.glorious.model.entity.product.Product;
import com.glorious.model.vo.product.label.InnerViewLabelProducts;
import com.glorious.utils.utils.bean.QBeanUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LabelAndProductView implements Serializable {

    private Long id;
    private String name;
    private List<InnerViewLabelProducts> products;

    public static LabelAndProductView byEntity(Label label, List<Product> products) {

        Optional<LabelAndProductView> ops = Optional.ofNullable(QBeanUtil.convert(label, LabelAndProductView.class));
        ops.ifPresent(l -> {
            if (products != null) {

                l.setProducts(
                        products.stream()
                                .map(InnerViewLabelProducts::byEntity)
                                .collect(Collectors.toList())
                );
            }
            l.setId(label.getId());
        });

        return ops.orElse(new LabelAndProductView());
    }
}
