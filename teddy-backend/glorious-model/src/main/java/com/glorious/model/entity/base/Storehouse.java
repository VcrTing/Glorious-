package com.glorious.model.entity.base;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.glorious.model.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@TableName("bas_storehouse")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Storehouse extends BaseEntity {

    private String name;

    private String phone_no;

    private String facebook;

    private String contact_person;

    private String address;

    private String remark;
}