package com.glorious.framework.component;

import com.glorious.common.define.auth.EnumSecurityRole;
import com.glorious.framework.component.tools.SecurityTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Component("Access")
public class AccessRoleComponent {

    @Autowired
    SecurityTool securityTool;

    /**
    * 是否是 ADMIN
    */
    public boolean admin() {
        return securityTool.getAuthoritiesSet().contains(EnumSecurityRole.ADMIN.value());
    }

    /**
     * 是否是 CASHIER
     */
    public boolean cashier() {
        Set<String> s = securityTool.getAuthoritiesSet();
        return s.contains(EnumSecurityRole.ADMIN.value()) || s.contains(EnumSecurityRole.CASHIER.value());
    }
}
