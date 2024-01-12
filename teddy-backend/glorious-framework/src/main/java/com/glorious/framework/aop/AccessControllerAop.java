package com.glorious.framework.aop;

import com.glorious.common.anno.Admin;
import com.glorious.common.anno.Cashier;
import com.glorious.common.define.auth.EnumSecurityRole;
import com.glorious.common.mvc.AjaxResult;
import com.glorious.framework.component.AccessRoleComponent;
import com.glorious.framework.component.tools.SecurityTool;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
* 自定义 权限认证
*/
@Aspect
@Component
public class AccessControllerAop {

    @Autowired
    AccessRoleComponent accessRoleComponent;

    // 包名
    final static String PAKAGE = "com.glorious.system";
    // 针对所有 controller
    final static String PATTERN = "execution(* " + PAKAGE + ".*.controller.*.*(..))";


    @Pointcut(PATTERN)
    public void po() { }

    @Around("po()")
    public Object controller(ProceedingJoinPoint jp) throws Throwable {

        Method method = AopUtil.getMethod(jp);
        Admin admin = method.getDeclaredAnnotation(Admin.class);

        if (admin != null) {
            // 若当前用户权限，不是 admin权限
            if (!accessRoleComponent.admin()) {
                return AjaxResult.error(HttpStatus.FORBIDDEN, "您没有ADMIN权限，无法访问该链接");
            }
        } else {
            Cashier cashier = method.getDeclaredAnnotation(Cashier.class);
            if (cashier != null) {
                // 若当前用户权限，不是 cashier权限
                if (!accessRoleComponent.cashier()) {
                    return AjaxResult.error(HttpStatus.FORBIDDEN, "您没有CASHIER权限，无法访问该链接");
                }
            }
        }

        // 放行
        return jp.proceed(jp.getArgs());
    }
}
