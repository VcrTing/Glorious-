package com.glorious.model.form.restock;

import cn.hutool.json.JSONUtil;
import com.glorious.model.entity.restock.Invoice;
import com.glorious.model.form.restock.invoice.InvoiceFormRestocks;
import com.glorious.utils.utils.basic.QTypeUtil;
import com.glorious.utils.utils.bean.QBeanUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceForm {

    private Long supplier;
    private Long storehouse;

    private Date date;
    private String invoice_id;
    private String invoice_address;
    private String delivery_address;

    private List<InvoiceFormRestocks> restocks;

    // 累加 数量
    public static Integer computedTotalQuantity(List<InvoiceFormRestocks> restocks) {
        return restocks.stream()
                .mapToInt(InvoiceFormRestocks::getTotal_quantity)
                .filter(Objects::nonNull)
                .reduce(0, Integer::sum);
    }
    // 累加 价格
    public static BigDecimal computedTotalAmount(List<InvoiceFormRestocks> restocks) {
        return restocks.stream()
                .map(InvoiceFormRestocks::getTotal_amount)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
    * 生成
    */
    public static Optional<Invoice> toEntity(InvoiceForm form) {
        Optional<Invoice> ops = Optional.ofNullable(QBeanUtil.convert(form, Invoice.class));
        ops.ifPresent(o -> {
            o.setSupplier_sql_id(form.supplier);
            o.setStorehouse_sql_id(form.storehouse);
            List<InvoiceFormRestocks> restocks = form.restocks;
            if (restocks != null) {
                o.setRestocks(JSONUtil.toJsonStr(restocks));
                o.setTotal_price(computedTotalAmount(restocks));
                o.setTotal_quantity(computedTotalQuantity(restocks));
            }
        });
        return ops;
    }
}
