package com.glorious.model.form.product;

import com.glorious.model.define.enums.EnumBoolean;
import com.glorious.model.entity.product.Label;
import com.glorious.utils.utils.bean.QBeanUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LabelForm {

    @NotNull(message = "标签名称不为空")
    @Length(min = 1, max = 60, message = "标签名称应该处于 {min} 和 {max} 之间")
    private String name;

    private Boolean isShow;

    public static Optional<Label> toEntity(LabelForm form) {
        Optional<Label> ops = Optional.ofNullable(QBeanUtil.convert(form, Label.class));
        ops.ifPresent(l -> {
            l.setIs_show(EnumBoolean.value(form.getIsShow()));
        });
        return ops;
    }
}
