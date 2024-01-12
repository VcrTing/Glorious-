package com.glorious.system.restock.controller;

import com.glorious.common.anno.Admin;
import com.glorious.common.define.webmvc.WebMvcRouter;
import com.glorious.common.mvc.AjaxResult;
import com.glorious.model.form.base.SupplierForm;
import com.glorious.model.form.restock.InvoiceForm;
import com.glorious.model.param.InvoiceParam;
import com.glorious.system.restock.service.impl.InvoiceServiceImpl;
import com.glorious.system.restock.webmvc.InvoiceWebDefine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(InvoiceWebDefine.API)
public class InvoiceController {

    @Autowired
    InvoiceServiceImpl service;

    /**
     * 分页 查询
     */
    @Admin
    @GetMapping
    public AjaxResult pag(InvoiceParam param) { return service.pag(param); }

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
    public AjaxResult pos(@RequestBody InvoiceForm form) {
        return service.pos(form);
    }

}
