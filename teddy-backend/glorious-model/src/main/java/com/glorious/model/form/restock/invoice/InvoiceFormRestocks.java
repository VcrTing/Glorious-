package com.glorious.model.form.restock.invoice;

import cn.hutool.json.JSONUtil;
import com.glorious.model.entity.restock.Restock;
import com.glorious.utils.utils.bean.QBeanUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceFormRestocks {

    // 产品名称
    private String name;
    // 产品 ID
    private Long product;
    // 折扣
    private String discount;

    // 最低价格
    private BigDecimal lowest_price;
    // 售价
    private BigDecimal selling_price;
    // 单价
    private BigDecimal unit_price;
    // 最新入货价格
    private BigDecimal net_price;

    // 总计 数量
    private Integer total_quantity;
    // 总计 价格
    private BigDecimal total_amount;

    // 样式 入货数据
    private List<InvoiceFormRestocksVariations> variations;

    /**
    * 生成 入货表单
    */
    public static Restock toRestock(
            InvoiceFormRestocks form,
            Long invoice_sql_id,
            Long supplier_sql_id,
            Long storehouse_sql_id
            ) {
        Restock res = QBeanUtil.convert(form, Restock.class);

        res.setProduct_sql_id(form.product);
        res.setInvoice_sql_id(invoice_sql_id);
        res.setSupplier_sql_id(supplier_sql_id);
        res.setStorehouse_sql_id(storehouse_sql_id);

        res.setRestock_price(form.net_price);
        res.setRestock_distribute(JSONUtil.toJsonStr(form.variations));
        res.setRestock_date(new Date());

        return res;
    }
}
