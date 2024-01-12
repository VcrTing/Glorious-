package com.glorious.model.vo.restock;

import com.glorious.model.dto.restock.InvoiceDto;
import com.glorious.model.entity.base.Storehouse;
import com.glorious.model.entity.base.Supplier;
import com.glorious.model.entity.restock.Invoice;
import com.glorious.utils.utils.bean.QBeanUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceSimplyView {

    private Long id;

    private Date date;
    private String invoice_id;

    private BigDecimal total_price;
    private Integer total_quantity;

    private Supplier supplier;
    private Storehouse storehouse;

    public static InvoiceSimplyView byDto(InvoiceDto dto) {
        Optional<InvoiceSimplyView> ops = Optional.ofNullable(QBeanUtil.convert(dto, InvoiceSimplyView.class));
        ops.ifPresent(o -> {
            o.setId(dto.getId());
        });
        return ops.orElse(null);
    }

    public static List<InvoiceSimplyView> byDtoList(List<InvoiceDto> invoices) {
        return invoices.stream()
                .map(InvoiceSimplyView::byDto)
                .filter(Objects::nonNull).collect(Collectors.toList());
    }
}
