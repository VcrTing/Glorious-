package com.glorious.system.backend.controller;

import com.glorious.common.anno.Admin;
import com.glorious.common.anno.Cashier;
import com.glorious.common.mvc.AjaxResult;
import com.glorious.common.define.webmvc.WebMvcRouter;
import com.glorious.model.form.custom.CustomerLevelForm;
import com.glorious.model.param.BaseParam;
import com.glorious.system.backend.service.impl.CustomerLevelServiceImpl;
import com.glorious.system.backend.webmvc.CustomerLevelWebDefine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CustomerLevelWebDefine.API)
public class CustomerLevelController {

    @Autowired
    CustomerLevelServiceImpl service;

    /**
     * 分页 查询
     */
    @Cashier
    @GetMapping
    public AjaxResult pag(BaseParam param) { return service.pag(param); }

    /**
     * 单个
     */
    @Admin
    @GetMapping(WebMvcRouter.ID)
    public AjaxResult one(@PathVariable Long id) { return service.one(id); }

    /**
     * 新增
     */
    @Admin
    @PostMapping
    public AjaxResult pos(@RequestBody CustomerLevelForm form) {
        return service.pos(form);
    }

    /**
     * 修改
     */
    @Admin
    @PatchMapping(WebMvcRouter.ID)
    public AjaxResult upd(@PathVariable Long id, @RequestBody CustomerLevelForm form) {
        return service.upd(id, form);
    }

    /**
     * 删除
     */
    @Admin
    @DeleteMapping(WebMvcRouter.ID)
    public AjaxResult del(@PathVariable Long id) { return service.del(id); }
}
