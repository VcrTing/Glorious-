package com.glorious.model.define.enums;

import com.baomidou.mybatisplus.annotation.IEnum;

public enum EnumSex implements IEnum<Integer> {

    M(1),
    F(0);

    EnumSex(Integer sex) { this.v = sex; }

    private final Integer v;

    @Override
    public Integer getValue() {
        return this.v;
    }
}
