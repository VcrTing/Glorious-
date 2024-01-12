package com.glorious.system.auth.controller;

import com.glorious.common.mvc.AjaxResult;
import com.glorious.model.form.auth.SystemAuthLoginForm;
import com.glorious.model.form.auth.SystemAuthRegisterForm;
import com.glorious.system.auth.service.AuthService;
import com.glorious.system.auth.webmvc.AuthWebDefine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class AuthController {

    @Autowired
    AuthService service;

    /**
    * 登录
    */
    @PostMapping(AuthWebDefine.LOGIN)
    public AjaxResult login(@RequestBody @Validated SystemAuthLoginForm form) { return service.login(form); }

    /**
     * 创建超级用户
     */
    @PostMapping(AuthWebDefine.REGISTER)
    public AjaxResult creatAdministrator(@RequestBody @Validated SystemAuthRegisterForm form) { return service.creatAdministrator(form); }

    /**
    * 登录过后查询 用户信息
    */
    @GetMapping(AuthWebDefine.USER_INFO)
    public AjaxResult userInfo() { return service.userInfo(); }

}
