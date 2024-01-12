package com.glorious.model.entity.sys;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.glorious.model.entity.BaseEntity;
import com.glorious.model.entity.base.Storehouse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("sys_user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    private String username;

    private String email;

    private String password;

    private String name;

    private String phone_no;

    private Long storehouse_sql_id;

    private Short is_admin;
}
