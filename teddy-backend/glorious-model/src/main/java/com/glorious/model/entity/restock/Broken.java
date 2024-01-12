package com.glorious.model.entity.restock;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.glorious.model.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@TableName("sok_broken")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Broken extends BaseEntity {

    private Date date;

    private Short quantity;

    private String remarks;

    private Long storehouse_sql_id;

    private Long product_sql_id;

    private Long variation_sql_id;
}
