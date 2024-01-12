package com.glorious.model.define.enums;

import com.baomidou.mybatisplus.annotation.IEnum;

import java.util.Objects;

public enum EnumBoolean implements IEnum<Short> {

    TRUE((short)1),
    FALSE((short)0);

    EnumBoolean(Short v) { this.v = v; }

    private final Short v;

    public Short getValue() {
        return this.v;
    }

    public static Short value(Boolean isTrue) { return (isTrue == null || !isTrue) ? EnumBoolean.FALSE.getValue() : EnumBoolean.TRUE.getValue(); }
    public static Boolean value(Short num) {
        return Objects.equals(EnumBoolean.TRUE.getValue(), num);
    }
}