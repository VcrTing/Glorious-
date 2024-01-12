package com.glorious.system.auth.controller;

import com.glorious.common.mvc.AjaxResult;
import com.glorious.common.define.webmvc.WebMvcRouter;
import com.glorious.model.form.sys.UserForm;
import com.glorious.model.param.BaseParam;
import com.glorious.system.auth.service.impl.UserServiceImpl;
import com.glorious.system.auth.webmvc.UserWebDefine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserServiceImpl service;

    /**
     * 分页 查询
     */
    @PreAuthorize("@Access.cashier()")
    @GetMapping(UserWebDefine.API)
    public AjaxResult pag(BaseParam param) { return service.pag(param); }

    /**
     * 单个
     */
    @PreAuthorize("@Access.admin()")
    @GetMapping(UserWebDefine.API + WebMvcRouter.ID)
    public AjaxResult one(@PathVariable Long id) { return service.one(id); }

    /**
     * 新增
     */
    @PreAuthorize("@Access.admin()")
    @PostMapping(UserWebDefine.API)
    public AjaxResult pos(@RequestBody UserForm form) {
        return service.pos(form);
    }

    /**
     * 修改
     */
    @PreAuthorize("@Access.admin()")
    @PatchMapping(UserWebDefine.PATCH + WebMvcRouter.ID)
    public AjaxResult upd(@PathVariable Long id, @RequestBody UserForm form) {
        return service.upd(id, form);
    }
}