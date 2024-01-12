package com.glorious.common.define.auth;

public enum EnumSecurityRole {

    ADMIN("ADMIN"),
    CASHIER("CASHIER");

    EnumSecurityRole(String v) { this.v = v; }

    private final String v;

    public String value() { return this.v; }
}
