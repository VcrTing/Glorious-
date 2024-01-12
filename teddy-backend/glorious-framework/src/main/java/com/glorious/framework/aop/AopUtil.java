package com.glorious.framework.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

public abstract class AopUtil
{
    /**
    * 获取方法
    */
    public static Method getMethod(JoinPoint jp) {
        Signature signature = jp.getSignature();
        Method method = null;

        String methodName = signature.getName();
        Method[] methods = signature.getDeclaringType().getDeclaredMethods();
        for (Method m: methods) {
            if (m.getName().equals(methodName)) {
                method = m;
            }
        }
        return method;
    }

    /**
    * 获取某注解
    */
    public static <T extends Annotation> T getMethodAnnotation(JoinPoint jp, Class<T> annoClass) {
        Method method = getMethod(jp);
        return method.getDeclaredAnnotation(annoClass);
    }
    /**
    * 获取全部注解
    */
    public static Annotation[] getMethodAnnotations(JoinPoint jp) {
        Method method = getMethod(jp);
        return method.getDeclaredAnnotations();
    }
}
