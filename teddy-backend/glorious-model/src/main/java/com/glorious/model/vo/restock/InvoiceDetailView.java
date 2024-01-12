package com.glorious.model.vo.restock;

import cn.hutool.json.JSONUtil;
import com.glorious.model.dto.restock.InvoiceDto;
import com.glorious.model.entity.base.Storehouse;
import com.glorious.model.entity.base.Supplier;
import com.glorious.model.form.restock.invoice.InvoiceFormRestocks;
import com.glorious.utils.utils.bean.QBeanUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDetailView {

    private Long id;

    private Date date;
    private String invoice_id;

    private BigDecimal total_price;
    private Integer total_quantity;

    private Supplier supplier;
    private Storehouse storehouse;

    private List<InvoiceFormRestocks> restocks;

    public static InvoiceDetailView byDto(InvoiceDto dto) {
        InvoiceDetailView view = QBeanUtil.convert(dto, InvoiceDetailView.class);
        if (view != null) {
            view.setRestocks(JSONUtil.toList(dto.getRestocks(), InvoiceFormRestocks.class));
        }
        return view;
    }

    public static List<InvoiceDetailView> byDtoList(List<InvoiceDto> invoices) {
        return invoices.stream().map(InvoiceDetailView::byDto).collect(Collectors.toList());
    }
}
