package com.glorious.system.product.controller;

import com.glorious.common.anno.Admin;
import com.glorious.common.mvc.AjaxResult;
import com.glorious.common.define.webmvc.WebMvcRouter;
import com.glorious.model.entity.product.Product;
import com.glorious.model.form.product.VariationForm;
import com.glorious.model.param.BaseParam;
import com.glorious.system.product.service.impl.ProductServiceImpl;
import com.glorious.system.product.service.impl.VariationServiceImpl;
import com.glorious.system.product.webmvc.VariationWebDefine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(VariationWebDefine.API)
public class VariationController {

    @Autowired
    VariationServiceImpl service;

    /**
     * 新增
     */
    @Admin
    @PostMapping
    public AjaxResult pos(@RequestBody VariationForm form) {
        return service.pos(form);
    }

    /**
     * 修改
     */
    @Admin
    @PatchMapping(WebMvcRouter.ID)
    public AjaxResult upd(@PathVariable Long id, @RequestBody VariationForm form) {
        return service.upd(id, form);
    }
}
