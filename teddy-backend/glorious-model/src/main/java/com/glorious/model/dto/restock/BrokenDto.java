package com.glorious.model.dto.restock;

import com.glorious.model.entity.base.Storehouse;
import com.glorious.model.entity.product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrokenDto {

    private Long id;

    private Date date;
    private String remarks;
    private Short quantity;

    private String product_id;
    private String product_name;

    private String storehouse_name;

    private Product product;
    private Storehouse storehouse;
}
