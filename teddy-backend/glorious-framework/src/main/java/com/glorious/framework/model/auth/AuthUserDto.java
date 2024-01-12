package com.glorious.framework.model.auth;

import com.glorious.utils.utils.bean.QBeanUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserDto implements Serializable {

    private Long userId;
    private String username;
    private Short isAdmin;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 过期时间 默认 24 小时
     */
    private Long expireTime;

    public static AuthUserDto byEntity(AuthUser authUser) {
        return QBeanUtil.convert(authUser, AuthUserDto.class);
    }

    public static AuthUser toEntity(AuthUserDto authUserDto) {
        return QBeanUtil.convert(authUserDto, AuthUser.class);
    }
}
