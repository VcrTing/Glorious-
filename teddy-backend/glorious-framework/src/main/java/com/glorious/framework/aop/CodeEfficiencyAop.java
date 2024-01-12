package com.glorious.framework.aop;

import com.glorious.common.utils.ServletUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Aspect
@Component
public class CodeEfficiencyAop {

    // 包名
    final static String PAKAGE = "com.glorious.system";
    // 针对所有 controller
    final static String PATTERN = "execution(* " + PAKAGE + ".*.controller.*.*(..))";

    final static long DEF_SPEED_LIMIT = 380;

    @Pointcut(PATTERN)
    public void po() { }

    @Around("po()")
    public Object interceptor(ProceedingJoinPoint jp) throws Throwable {

        // 记录开始时间
        long start = System.currentTimeMillis();
        // 这里放置需要计算执行时间的代码
        Object result = jp.proceed(jp.getArgs());
        // 记录结束时间
        long duration = System.currentTimeMillis() - start;

        // 打印速度
        if (duration > DEF_SPEED_LIMIT) {
            HttpServletRequest request = ServletUtil.getRequest();

            StringBuilder sb = new StringBuilder("本次代码执行速度较慢");
            sb
                    .append("，时间 = ").append(duration)
                    .append("，方法名称 = ").append(jp.getSignature().getName());
            if (request != null) {
                sb.append("，LINK = ").append(request.getRequestURI());
            }

            log.debug(sb.toString());
        }
        return result;
    }
}
