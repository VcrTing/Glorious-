package com.glorious.model.entity.custom;

import com.baomidou.mybatisplus.annotation.TableName;
import com.glorious.model.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@TableName("csm_custom_level")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerLevel extends BaseEntity {

    private String name;
    private String discount;
}
