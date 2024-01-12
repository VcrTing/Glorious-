package com.glorious.common.exception;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
public class QAuthException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误提示
     */
    private String message;

    /**
     * 空构造方法，避免反序列化问题
     */

    public QAuthException(String message) {
        this.message = message;
        this.code = HttpStatus.FORBIDDEN.value();
    }

    public QAuthException(String message, HttpStatus code) {
        this.message = message;
        this.code = code.value();
    }
}
