package com.glorious.model.form.custom;

import com.glorious.model.entity.custom.CustomerLevel;
import com.glorious.utils.utils.bean.QBeanUtil;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Optional;

@Data
public class CustomerLevelForm {

    @Length(min = 2, max = 90, message = "名字长度介于 {min} - {max} 之间")
    private String name;

    @NotNull(message = "折扣数字 不为空")
    @Digits(integer = 1, fraction = 3, message = "折扣数字位于 0 - 1 之间")
    private BigDecimal discount;

    /**
    * 转去 entity
    * @params
    * @return
    */
    public static <T> Optional<CustomerLevel> toEntity(CustomerLevelForm form) {
        Optional<CustomerLevel> ops = Optional.ofNullable(QBeanUtil.convert(form, CustomerLevel.class));
        ops.ifPresent(entity -> entity.setDiscount(form.getDiscount().toString()));
        return ops;
    }
}
