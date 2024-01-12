package com.glorious.system.auth.webmvc;

import com.glorious.common.define.auth.SecurityAuthRouter;

public interface AuthWebDefine {


    String LOGIN = SecurityAuthRouter.LOGIN;

    String REGISTER = SecurityAuthRouter.REGISTER;

    String USER_INFO = "/users-permissions/current_user";
}
