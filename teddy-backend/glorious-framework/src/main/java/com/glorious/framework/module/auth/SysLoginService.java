package com.glorious.framework.module.auth;

import com.glorious.framework.model.auth.AuthUser;
import com.glorious.framework.component.tools.SecurityTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SysLoginService {

    @Autowired
    AuthenticationManager manager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    TokenService tokenService;

    @Autowired
    SecurityTool securityTool;

    /**
    * 登录成功返回 TOKEN，已代表上下文已有该用户
    */
    public Object login(String username, String password) {

        Authentication authentication = manager.authenticate( securityTool.genNamePassToken(username, password) );

        log.debug("登录 authentication = " + authentication);
        if (authentication == null) return "用户名/邮箱 或者 密码 错误，认证失败";

        Object principal = authentication.getPrincipal();
        if (principal == null) return "该用户可能未注册，认证失败";

        return tokenService.buildAuthUser((AuthUser) principal);
    }
}
