package com.glorious.model.entity.custom;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.glorious.model.define.enums.EnumSex;
import com.glorious.model.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@TableName("csm_customer")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends BaseEntity {

    private String name;

    private String email;

    private String phone_no;

    private Date create_date;

    private Date birthdate;

    private EnumSex sex;

    private String member_id;

    private String address;

    private String remarks;

    private Long customer_level_sql_id;
}
