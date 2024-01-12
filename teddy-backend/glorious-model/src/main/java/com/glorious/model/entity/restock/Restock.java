package com.glorious.model.entity.restock;

import com.baomidou.mybatisplus.annotation.TableName;
import com.glorious.model.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@TableName("sok_restock")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restock extends BaseEntity {

    private Date restock_date;

    private BigDecimal restock_price;
    private BigDecimal lowest_price;
    private BigDecimal selling_price;

    private Long invoice_sql_id;
    private Long product_sql_id;
    private Long supplier_sql_id;
    private Long storehouse_sql_id;

    // 总计 数量
    private Integer total_quantity;

    // 总计 价格
    private BigDecimal total_amount;

    // 样式 入货数据
    private String restock_distribute;

    // 当前入货平均价
    private BigDecimal average_restock_price;
}
