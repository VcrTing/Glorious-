package com.glorious.system.order.controller;

import com.glorious.common.anno.Admin;
import com.glorious.common.mvc.AjaxResult;
import com.glorious.common.define.webmvc.WebMvcRouter;
import com.glorious.model.param.OrderParam;
import com.glorious.system.order.service.impl.OrderServiceImpl;
import com.glorious.system.order.webmvc.OrderWebDefine;
import com.glorious.utils.utils.basic.QTypeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(OrderWebDefine.API)
public class OrderController {

    @Autowired
    OrderServiceImpl service;

    /**
     * 分页 查询
     */
    // @PreAuthorize("@Access.admin()")
    @Admin
    @GetMapping
    public AjaxResult pag(OrderParam param) { return service.pag(param); }

    /**
     * 单个
     */
    @Admin
    @GetMapping(WebMvcRouter.ID)
    public AjaxResult one(@PathVariable Object id) {
        return QTypeUtil.isLong(id) ? service.one(QTypeUtil.serLong(id)) : service.one(id.toString());
    }

}
