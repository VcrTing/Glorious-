package com.glorious.model.dto.restock;

import com.glorious.model.entity.BaseEntity;
import com.glorious.model.entity.base.Storehouse;
import com.glorious.model.entity.base.Supplier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDto extends BaseEntity {

    private Date date;
    private String invoice_id;

    private BigDecimal total_price;
    private Integer total_quantity;

    private Supplier supplier;
    private Storehouse storehouse;

    private String restocks;
}
