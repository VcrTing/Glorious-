package com.glorious.framework.handler.auth;

import com.alibaba.druid.support.json.JSONUtils;
import com.glorious.common.mvc.AjaxResult;
import com.glorious.common.utils.ServletUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SecurityAuthFailureHandier implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        String msg = "认证 TOKEN 失败，请登录。";
        ServletUtil.renderString(response, status, JSONUtils.toJSONString(AjaxResult.error(status, msg)));
    }
}
