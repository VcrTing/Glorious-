package com.glorious.model.entity.product;

import com.baomidou.mybatisplus.annotation.TableName;
import com.glorious.model.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@TableName("pod_variation")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Variation extends BaseEntity implements Serializable {

    private String name;
}
