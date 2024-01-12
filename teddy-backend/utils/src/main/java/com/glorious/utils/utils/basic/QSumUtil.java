package com.glorious.utils.utils.basic;

import java.math.BigDecimal;

public abstract class QSumUtil {

    /**
    * 数值比较
    * @params
    * @return
    */
    public static boolean biggerInt(Integer a, Integer b) { return mustInt(a) >= mustInt(b); }
    public static boolean lessInt(Integer a, Integer b) {
         return mustInt(a) <= mustInt(b);
    }

    /**
     * 基本运算
     * @params
     * @return
     */
    public static Integer mustInt(Integer num) { return (num == null) ? 0 : num; }
    public static BigDecimal mustDecimal(BigDecimal num) { return (num == null) ? BigDecimal.ZERO : num; }
    public static Integer sub(Integer a, Integer b) {
        return mustInt(a) - mustInt(b);
    }
    public static BigDecimal sub(BigDecimal a, BigDecimal b) {
        return mustDecimal(a).subtract(mustDecimal(b));
    }
    public static Integer add(Integer a, Integer b) {
        return mustInt(a) + mustInt(b);
    }
    public static BigDecimal add(BigDecimal a, BigDecimal b) {
        return mustDecimal(a).add(mustDecimal(b));
    }
}
