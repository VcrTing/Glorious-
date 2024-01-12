package com.glorious.system.auth.service;

import com.glorious.common.mvc.AjaxResult;
import com.glorious.model.form.auth.SystemAuthLoginForm;
import com.glorious.model.form.auth.SystemAuthRegisterForm;

public interface AuthService {

    /**
    * 登录
    */
    AjaxResult login(SystemAuthLoginForm form);

    /**
     * 注册
     */
    AjaxResult creatAdministrator(SystemAuthRegisterForm form);

    /**
    * 用户信息
    */
    AjaxResult userInfo();
}
