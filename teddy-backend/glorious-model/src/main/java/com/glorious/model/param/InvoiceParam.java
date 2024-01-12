package com.glorious.model.param;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.glorious.model.entity.restock.Invoice;
import com.glorious.model.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.Date;

@Data
public class InvoiceParam extends BaseParam {

    private String invoice_id;
    private Long supplier;
    private String date;

    public Boolean hasInvoice_id() {
        return StringUtils.hasText(invoice_id);
    }
    public Boolean hasSupplier() {
        return supplier != null;
    }
    public Boolean hasDate() {
        return StringUtils.hasText(date);
    }

    private String sort;
    private Short page;
    private Short size;
    private String search;

    public Boolean hasSort() {
        return sort != null && (!sort.trim().isEmpty());
    }
    public Boolean isAsc() {
        return hasSort() && sort.toUpperCase().contains("ASC");
    }
    public Boolean hasSearch() {
        return search != null && (!search.trim().isEmpty());
    }

    /**
    *
    * @params
    * @return
    */
    public QueryWrapper<Invoice> queryWrapper() {
        InvoiceParam invoiceParam = this;

        QueryWrapper<Invoice> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderBy(invoiceParam.hasSort(), invoiceParam.isAsc(), "me.id");

        if (invoiceParam.hasInvoice_id())
            queryWrapper.like("me.invoice_id", invoiceParam.getInvoice_id()).or();

        if (invoiceParam.hasDate())
            queryWrapper.lt("me.date", invoiceParam.getDate()).or();

        if (invoiceParam.hasSupplier())
            queryWrapper.eq("me.supplier_sql_id", invoiceParam.getSupplier()).or();

        return queryWrapper;
    }
}
