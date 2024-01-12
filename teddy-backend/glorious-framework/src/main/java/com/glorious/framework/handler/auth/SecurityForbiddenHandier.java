package com.glorious.framework.handler.auth;

import com.alibaba.druid.support.json.JSONUtils;
import com.glorious.common.mvc.AjaxResult;
import com.glorious.common.utils.ServletUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SecurityForbiddenHandier implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) {
        HttpStatus status = HttpStatus.FORBIDDEN;
        String msg = "您没有权限访问该链接。";
        ServletUtil.renderString(response, status, JSONUtils.toJSONString(AjaxResult.error(status, msg)));
    }
}
