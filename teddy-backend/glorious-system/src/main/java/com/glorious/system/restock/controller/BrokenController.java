package com.glorious.system.restock.controller;

import com.glorious.common.anno.Admin;
import com.glorious.common.define.webmvc.WebMvcRouter;
import com.glorious.common.mvc.AjaxResult;
import com.glorious.model.form.product.LabelForm;
import com.glorious.model.form.restock.BrokenForm;
import com.glorious.model.param.BaseParam;
import com.glorious.model.param.BrokenParam;
import com.glorious.system.restock.service.impl.BrokenServiceImpl;
import com.glorious.system.restock.webmvc.BrokenWebDefine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(BrokenWebDefine.API)
public class BrokenController {

    @Autowired
    BrokenServiceImpl service;

    /**
     * 分页 查询
     */
    @PreAuthorize("@Access.admin()")
    @GetMapping
    public AjaxResult pag(BrokenParam param) { return service.pag(param); }

    /**
     * 新增 坏货
     */
    @Admin
    @PostMapping
    public AjaxResult pos(@RequestBody BrokenForm form) {
        return service.pos(form);
    }

    /**
     * 取消 坏货
     */
    @Admin
    @DeleteMapping(WebMvcRouter.ID)
    public AjaxResult del(@PathVariable Long id) {
        return service.del(id);
    }
}
