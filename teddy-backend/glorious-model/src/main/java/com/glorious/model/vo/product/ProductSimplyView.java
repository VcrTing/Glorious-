package com.glorious.model.vo.product;

import cn.hutool.json.JSONUtil;
import com.glorious.model.define.converter.LabelIdConverter;
import com.glorious.model.entity.product.Label;
import com.glorious.model.entity.product.Product;
import com.glorious.model.entity.product.remark.ProductRemark;
import com.glorious.model.vo.product.product.InnerViewVariation;
import com.glorious.utils.utils.bean.QBeanUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSimplyView {

    private Long id;
    private String product_id;
    private String name;

    private String new_supplier;
    private Date new_restock_date;

    private Date create_date;

    private BigDecimal new_restock_price;
    private BigDecimal new_lowest_price;
    private BigDecimal new_selling_price;

    // 入货价 平均 价
    // private BigDecimal average_restock_price;

    // 总库存
    private Integer total_stock;

    // 标签
    private List<LabelView> labels;

    // 样式 的 库存
    private List<InnerViewVariation> variations;

    private List<ProductRemark> remarks;

    public static ProductSimplyView byEntity(Product product) {
        Optional<ProductSimplyView> ops = Optional.ofNullable(QBeanUtil.convert(product, ProductSimplyView.class));
        ops.ifPresent(p -> {
            p.setId(product.getId());
            if (StringUtils.hasText(product.getRemarks())) {
                p.setRemarks(JSONUtil.toList(product.getRemarks(), ProductRemark.class));
            }
        });
        return ops.orElse(null);
    }

    public static List<ProductSimplyView> byEntities(List<Product> entities) {
        return (entities == null) ? null :
                entities.stream()
                        .map(ProductSimplyView::byEntity)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());
    }
}
