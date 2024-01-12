package com.glorious.model.entity.product;

import com.baomidou.mybatisplus.annotation.TableName;
import com.glorious.model.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("pod_product_of_variation_and_storehouse")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductOfVariationAndStorehouse extends BaseEntity {

    private Integer quantity;

    private Long product_sql_id;
    private Long variation_sql_id;
    private Long storehouse_sql_id;

    // 乐观锁
    private Integer version;
}
