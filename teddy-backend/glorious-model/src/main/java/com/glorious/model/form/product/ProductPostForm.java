package com.glorious.model.form.product;

import cn.hutool.json.JSONUtil;
import com.glorious.model.define.converter.LabelIdConverter;
import com.glorious.model.entity.product.Product;
import com.glorious.model.entity.product.remark.ProductRemark;
import com.glorious.utils.utils.bean.QBeanUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductPostForm {

    @NotNull(message = "产品 ID 不为空")
    private String product_id;

    @NotNull(message = "产品 创建日期 不为空")
    private Date create_date;

    @NotNull(message = "产品 名称 不为空")
    @Length(min = 1, max = 90, message = "产品名称应该处于 {min} 和 {max} 之间")
    private String name;

    private List<Long> labels;

    private List<ProductRemark> remarks;

    public static Optional<Product> toEntity(ProductPostForm form) {
        Optional<Product> ops = Optional.ofNullable(QBeanUtil.convert(form, Product.class));
        ops.ifPresent(o -> {
            if (form.getRemarks() != null) {
                o.setRemarks(JSONUtil.toJsonStr(form.getRemarks()));
            }
            if (form.getLabels() != null) {
                o.setLabels(LabelIdConverter.init(form.getLabels()).getJson());
            }
        });
        return ops;
    }
}
