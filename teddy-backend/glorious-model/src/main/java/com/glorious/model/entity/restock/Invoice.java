package com.glorious.model.entity.restock;


import com.baomidou.mybatisplus.annotation.TableName;
import com.glorious.model.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@TableName("sok_invoice")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invoice extends BaseEntity {

    private BigDecimal total_price;
    private Integer total_quantity;

    private Long supplier_sql_id;
    private Long storehouse_sql_id;

    private Date date;
    private String invoice_id;
    private String invoice_address;
    private String delivery_address;

    // 入货详情
    private String restocks;

}
