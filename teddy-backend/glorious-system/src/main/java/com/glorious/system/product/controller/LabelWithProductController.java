package com.glorious.system.product.controller;

import com.glorious.common.anno.Admin;
import com.glorious.common.mvc.AjaxResult;
import com.glorious.system.product.service.impl.LabelWithProductServiceImpl;
import com.glorious.system.product.webmvc.LabelWebDefine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LabelWithProductController {

    @Autowired
    LabelWithProductServiceImpl service;

    /**
    * 产品新增标签
    */
    @Admin
    @PatchMapping(LabelWebDefine.PRODUCT_ADD_LABEL)
    public AjaxResult addLabel(@PathVariable Long pid, @PathVariable Long lid) {
        return service.addLabel(pid, lid);
    }
    /**
    * 产品删除标签
    */
    @Admin
    @PatchMapping(LabelWebDefine.PRODUCT_DEL_LABEL)
    public AjaxResult delLabel(@PathVariable Long pid, @PathVariable Long lid) {
        return service.delLabel(pid, lid);
    }
}
