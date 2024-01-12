package com.glorious.system.product.controller;

import com.glorious.common.anno.Admin;
import com.glorious.common.mvc.AjaxResult;
import com.glorious.common.define.webmvc.WebMvcRouter;
import com.glorious.model.form.product.ProductPatchForm;
import com.glorious.model.form.product.ProductPostForm;
import com.glorious.model.param.ProductParam;
import com.glorious.system.product.service.impl.ProductServiceImpl;
import com.glorious.system.product.webmvc.ProductWebDefine;
import com.glorious.utils.utils.basic.QTypeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ProductWebDefine.API)
public class ProductController {

    @Autowired
    ProductServiceImpl service;

    /**
     * 分页 查询
     */
    @Admin
    @GetMapping
    public AjaxResult pag(ProductParam param) { return service.pag(param); }

    /**
     * 单个
     */
    @Admin
    @GetMapping(WebMvcRouter.ID)
    public AjaxResult one(@PathVariable Object id) {
        return QTypeUtil.isLong(id) ?
                service.one(Long.valueOf(id.toString())) :
                service.one(id.toString());
    }

    /**
     * 新增
     */
    @Admin
    @PostMapping
    public AjaxResult pos(@RequestBody ProductPostForm form) {
        return service.pos(form);
    }

    /**
     * 修改
     */
    @Admin
    @PatchMapping(WebMvcRouter.ID)
    public AjaxResult upd(@PathVariable Long id, @RequestBody ProductPatchForm form) {
        return service.upd(id, form);
    }
}
