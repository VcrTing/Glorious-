package com.glorious.system.backend.controller;

import com.glorious.common.anno.Admin;
import com.glorious.common.anno.Cashier;
import com.glorious.common.mvc.AjaxResult;
import com.glorious.common.define.webmvc.WebMvcRouter;
import com.glorious.model.form.base.SupplierForm;
import com.glorious.model.param.BaseParam;
import com.glorious.system.backend.service.impl.SupplierServiceImpl;
import com.glorious.system.backend.webmvc.SupplierWebDefine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(SupplierWebDefine.API)
public class SupplierController {

    @Autowired
    SupplierServiceImpl service;

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
    public AjaxResult pos(@RequestBody SupplierForm form) {
        return service.pos(form);
    }

    /**
    * 修改
    */
    @Admin
    @PatchMapping(WebMvcRouter.ID)
    public AjaxResult upd(@PathVariable Long id, @RequestBody SupplierForm form) {
        return service.upd(id, form);
    }

}
