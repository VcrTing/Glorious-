package com.glorious.common.define.auth;

public interface SecurityAuthRouter {

    String ROOT = "/auth";

    String LOGIN = ROOT + "/local";

    String REGISTER = ROOT + "/register";
}
