package com.glorious.model.form.sys;

import com.glorious.model.define.enums.EnumBoolean;
import com.glorious.model.entity.sys.User;
import com.glorious.utils.utils.bean.QBeanUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserForm {

    private String username;

    private String password;

    @Email
    @Length(min = 2, max = 250, message = "郵箱长度介于 {min} - {max} 之间")
    private String email;

    @NotNull(message = "姓名不为空")
    @Length(min = 2, max = 60, message = "姓名长度介于 {min} - {max} 之间")
    private String name;

    @NotNull(message = "电话号码不为空")
    @Length(min = 4, max = 11, message = "电话号码长度介于 {min} - {max} 之间")
    private String phone_no;

    private Boolean isAdmin;

    @NotNull(message = "所属仓库不为空")
    private Long storehouse;

    public static Optional<User> toEntity(UserForm form) {
        Optional<User> ops = Optional.ofNullable(QBeanUtil.convert(form, User.class));
        ops.ifPresent( one -> {
            one.setIs_admin( EnumBoolean.value( form.getIsAdmin() ) );
            one.setStorehouse_sql_id(form.getStorehouse());
            one.setUsername( form.getUsername() != null ? form.getUsername() : form.getEmail());
        });
        return ops;
    }
}
