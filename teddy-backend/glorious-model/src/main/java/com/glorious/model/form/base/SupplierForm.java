package com.glorious.model.form.base;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Email;

import java.util.Date;

@Data
public class SupplierForm {

    @NotNull(message = "编号不为空")
    @Length(min = 2, max = 30, message = "编号长度介于 {min} - {max} 之间")
    private String supplier_id;

    @Email(message = "请输入正确的电邮地址")
    private String email;

    @Length(min = 2, max = 90, message = "名字长度介于 {min} - {max} 之间")
    private String name;

    @NotNull(message = "电话号码不为空")
    @Length(min = 4, max = 11, message = "电话号码长度介于 {min} - {max} 之间")
    private String phone_no;

    @NotNull(message = "创建日期不为空")
    private Date create_date;

    private String contact_person;

    @Length(min = 0, max = 250, message = "公司地址长度介于 {min} - {max} 之间")
    private String office_address;

    @Length(min = 0, max = 250, message = "工厂地址长度介于 {min} - {max} 之间")
    private String factory_address;

    private String remarks;
}
