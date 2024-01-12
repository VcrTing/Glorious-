package com.glorious.framework.model.auth;

import com.glorious.common.define.auth.EnumSecurityRole;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Slf4j
@Data
@NoArgsConstructor
public class AuthUser implements UserDetails {

    private String jwt;
    private Long userId;
    private String username;
    private String password;
    private Short isAdmin;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 过期时间 默认 24 小时
     */
    private Long expireTime;


    private Collection<? extends GrantedAuthority> permissions;

    /**
    * 创建默认 登录用户
    */
    public static AuthUser init(Long userId, String username, String password, Short isAdmin) {
        AuthUser res = new AuthUser();
        res.userId = userId;
        res.isAdmin = isAdmin;
        res.password = password;
        res.username = username;
        res.loginTime = System.currentTimeMillis();
        res.expireTime = 24 * 60 * 1000L;
        return res;
    }

    public static AuthUser bad() { return new AuthUser(); }

    /**
     * 生成 权限 的 方法
     */
    public static Collection<? extends GrantedAuthority> genAuthorities(Boolean isAdmin) {
        return Collections.singletonList( new SimpleGrantedAuthority(
                isAdmin ?
                        EnumSecurityRole.ADMIN.value() :
                        EnumSecurityRole.CASHIER.value() )
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (permissions != null) return permissions;
        permissions = genAuthorities(Objects.equals(isAdmin, (short) 1));
        return permissions;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
