package com.glorious.model.entity.base;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.glorious.model.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@TableName("bas_supplier")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Supplier extends BaseEntity {

    private String supplier_id;

    private String email;

    private String name;

    private String phone_no;

    private String remarks;

    private Date create_date;

    private String contact_person;

    private String office_address;

    private String factory_address;
}

