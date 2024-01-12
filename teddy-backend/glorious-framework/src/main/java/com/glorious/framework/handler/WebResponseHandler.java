package com.glorious.framework.handler;

import com.glorious.common.mvc.AjaxResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class WebResponseHandler implements ResponseBodyAdvice<Object> {

    // 启用 判断
    final static boolean ENABLE_ADVICE_JUDGE = true;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        /*
        if (!ENABLE_ADVICE_JUDGE) return false;
        ResponseAdvice anno = returnType.getMethodAnnotation(ResponseAdvice.class);
        if (anno == null) {
            anno = returnType.getDeclaringClass().getAnnotation(ResponseAdvice.class);
        }
        return anno != null;
         */
        return ENABLE_ADVICE_JUDGE;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // 转化 AjaxResult.code To ServerHttpResponse.statusCode
        if (body instanceof AjaxResult) ((AjaxResult) body).resolveCode().ifPresent(response::setStatusCode);
        return body;
    }
}
