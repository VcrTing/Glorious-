package com.glorious.common.define.auth;

public interface SecurityAccessConstants {

    /**
    * ADMIN 访问限制
    */
    String AUTH_ADMIN = "hasAuthority('" + EnumSecurityRole.ADMIN + "')";

    /**
    * 收银员访问限制
    */
    String AUTH_CASHIER = "hasAnyAuthority('" + EnumSecurityRole.ADMIN + "', '" + EnumSecurityRole.CASHIER + "')";
}
