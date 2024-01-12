package com.glorious.model.dto.restock;

import com.glorious.model.entity.base.Storehouse;
import com.glorious.model.entity.base.Supplier;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class RestockDto {

    private Long id;
    private Date restock_date;

    private BigDecimal restock_price;
    private BigDecimal lowest_price;
    private BigDecimal selling_price;

    private Supplier supplier;
    private Storehouse storehouse;

    // 总计 数量
    private Integer total_quantity;
    // 总计 价格
    private BigDecimal total_amount;
    // 样式 入货数据
    private String restock_distribute;

    // 实体
    // private Invoice invoice;
    // private Supplier supplier;
}
