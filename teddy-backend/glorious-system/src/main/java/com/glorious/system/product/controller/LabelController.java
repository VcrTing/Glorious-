package com.glorious.system.product.controller;

import com.glorious.common.anno.Admin;
import com.glorious.common.anno.Cashier;
import com.glorious.common.mvc.AjaxResult;
import com.glorious.common.define.webmvc.WebMvcRouter;
import com.glorious.model.entity.product.Label;
import com.glorious.model.form.product.LabelForm;
import com.glorious.model.param.BaseParam;
import com.glorious.system.product.service.impl.LabelServiceImpl;
import com.glorious.system.product.webmvc.LabelWebDefine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(LabelWebDefine.API)
public class LabelController {

    @Autowired
    LabelServiceImpl service;

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
    public AjaxResult pos(@RequestBody LabelForm form) {
        return service.pos(form);
    }

    /**
     * 修改
     */
    @Admin
    @PatchMapping(WebMvcRouter.ID)
    public AjaxResult upd(@PathVariable Long id, @RequestBody LabelForm form) {
        return service.upd(id, form);
    }

    /**
     * 删除
     */
    @Admin
    @DeleteMapping(WebMvcRouter.ID)
    public AjaxResult del(@PathVariable Long id) {
        return service.del(id);
    }
}
