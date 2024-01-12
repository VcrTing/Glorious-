package com.glorious.model.entity.order;


import com.baomidou.mybatisplus.annotation.TableName;
import com.glorious.model.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@TableName("odr_refunded_record")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefundedRecord extends BaseEntity {

    private Long order_sql_id;

    private Long profit_sql_id;

    private Long storehouse_sql_id;

    private String refunded_remarks;

    // 退款金额、数量
    private BigDecimal refunded_price;

    private Integer refunded_quantity;

    // 退款详情
    private String refunded_info;
}
