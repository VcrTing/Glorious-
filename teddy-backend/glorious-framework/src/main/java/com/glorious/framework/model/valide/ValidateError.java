package com.glorious.framework.model.valide;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidateError implements Serializable {
    private String field;
    private String msg;

    public static ValidateError def() {
        return new ValidateError("", "数据验证错误，请检查数据完整性");
    }
}
