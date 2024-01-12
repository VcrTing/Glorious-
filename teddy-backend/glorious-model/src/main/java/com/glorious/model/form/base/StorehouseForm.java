package com.glorious.model.form.base;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class StorehouseForm {

    @NotNull(message = "倉庫名稱不為空")
    @Length(min = 2, max = 120, message = "倉庫名稱长度介于 {min} - {max} 之间")
    private String name;

    @NotNull(message = "聯絡電話不為空")
    @Length(min = 4, max = 11, message = "电话号码长度介于 {min} - {max} 之间")
    private String phone_no;

    @Length(min = 0, max = 250, message = "關注/FOLLOW 值的长度介于 {min} - {max} 之间")
    private String facebook;

    @NotNull(message = "聯絡人姓名不為空")
    @Length(min = 2, max = 120, message = "聯絡人姓名长度介于 {min} - {max} 之间")
    private String contact_person;

    @Length(min = 0, max = 250, message = "地址长度介于 {min} - {max} 之间")
    private String address;

    private String remark;
}
