package com.glorious.model.form.restock;

import com.glorious.model.entity.restock.Broken;
import com.glorious.utils.utils.bean.QBeanUtil;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Optional;

@Data
public class BrokenForm {

    @NotNull(message = "壞貨日期不为空")
    private Date date;

    private String remarks;

    @NotNull(message = "壞貨數量不为空")
    private Short quantity;

    @NotNull(message = "壞貨产品不为空")
    private Long product_id;

    @NotNull(message = "壞貨樣式不为空")
    private Long variation;

    @NotNull(message = "壞貨倉庫不为空")
    private Long storehouse_id;

    public static Optional<Broken> toEntity(BrokenForm form) {
        Optional<Broken> ops = Optional.ofNullable(QBeanUtil.convert(form, Broken.class));
        ops.ifPresent(b -> {
            b.setProduct_sql_id(form.product_id);
            b.setVariation_sql_id(form.variation);
            b.setStorehouse_sql_id(form.storehouse_id);
        });

        return ops;
    }
}
