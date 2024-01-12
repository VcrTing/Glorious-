package com.glorious.common.utils;

import com.glorious.common.text.exception.TextsException;

public abstract class ExceptionTextUtil {

    /**
    * 錯誤信息 轉換成中文
    */
    public static String convertText(String msgEn) {
        return TextsException.convertText(msgEn);
    }
}
