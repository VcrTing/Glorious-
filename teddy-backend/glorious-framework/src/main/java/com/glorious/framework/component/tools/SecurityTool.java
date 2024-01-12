package com.glorious.framework.component.tools;

import com.glorious.common.define.auth.EnumSecurityRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings(value = {"unchecked", "rawtypes"})
@Slf4j
@Component
public class SecurityTool {

    /**
     * 生成 登录前的 验证令牌
     */
    public UsernamePasswordAuthenticationToken genNamePassToken(String username, String password) {
        if (StringUtils.hasText(username) && StringUtils.hasText(password)) return new UsernamePasswordAuthenticationToken(username, password);
        return new UsernamePasswordAuthenticationToken("", "");
    }

    /**
     * 从上下文获取 当前登录的 AUTH USER
     */
    public <T extends UserDetails> T getPrincipal() {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        return (token.getPrincipal() != null) ? (T) token.getPrincipal() : null;
    }
    /**
     * 从上下文获取 认证信息 里面的 权限集合
     */
    public Collection<? extends GrantedAuthority> getAuthoritiesFromContext() {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();;
        return (authenticationToken != null) ? authenticationToken.getAuthorities() : null;
    }

    /**
     * 获取权限文字 SET
     */
    public Set<String> getAuthoritiesSet() {
        Collection<? extends GrantedAuthority> cs = getAuthoritiesFromContext();
        return (cs != null) ? AuthorityUtils.authorityListToSet(cs) : new HashSet<>(0);
    }

    /**
    * 是否包含
    */
    public boolean has(String role) {
        return getAuthoritiesSet().contains(role);
    }
}
