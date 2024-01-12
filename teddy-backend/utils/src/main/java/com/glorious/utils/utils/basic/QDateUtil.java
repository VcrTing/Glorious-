package com.glorious.utils.utils.basic;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class QDateUtil {

    static String DEF_FMT = "yyyy-MM-dd";
    static String DEF_FMT_LONG = DEF_FMT + " HH:mm:ss";

    /**
     * 转换 时间
     */
    public static Date convert(Object dateString, boolean isExact) {
        try {
            String src = dateString.toString().trim();
            return new SimpleDateFormat(isExact ? DEF_FMT_LONG : DEF_FMT).parse(src); }
        catch (Exception ignored) { } return null;
    }
    public static String convert(Date date, boolean isExactDate) {
        try {
            return new SimpleDateFormat(isExactDate ? DEF_FMT_LONG : DEF_FMT).format(date); }
        catch (Exception ignored) { } return null;
    }

    /**
    * 获取当前
    */
    public static String now(boolean isExact) {
        return convert(new Date(), isExact);
    }
    public static String now() {
        return convert(new Date(), false);
    }
}
