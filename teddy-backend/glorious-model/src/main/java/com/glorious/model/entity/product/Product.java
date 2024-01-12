package com.glorious.model.entity.product;


import com.baomidou.mybatisplus.annotation.TableName;
import com.glorious.model.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@TableName("pod_product")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity {

    private String product_id;

    private Date create_date;

    private String name;

    private String remarks;

    // 入貨 時 更新 為 最新的數據
    private Date new_restock_date;

    private BigDecimal new_restock_price;

    private BigDecimal new_selling_price;

    private BigDecimal new_lowest_price;

    // 最新的 供應商 是:
    private String new_supplier;

    private Long new_supplier_sql_id;

    // 标签 多對多 JSON 数据
    private String labels;

    // 入货平均价格
    private BigDecimal average_restock_price;
}
