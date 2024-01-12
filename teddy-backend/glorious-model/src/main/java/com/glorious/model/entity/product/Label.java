package com.glorious.model.entity.product;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.glorious.model.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("pod_label")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Label extends BaseEntity {

    private String name;

    private Short is_show;
}
