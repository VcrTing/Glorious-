package com.glorious.system.order.controller;

import com.glorious.common.anno.Cashier;
import com.glorious.common.define.webmvc.WebMvcRouter;
import com.glorious.common.mvc.AjaxResult;
import com.glorious.model.form.order.RefundForm;
import com.glorious.system.order.service.impl.RefundServiceImpl;
import com.glorious.system.order.service.implExtra.OrderServiceImplExtra;
import com.glorious.system.order.webmvc.OrderWebDefine;
import com.glorious.system.order.webmvc.RefundWebDefine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping(RefundWebDefine.API)
public class RefundController {

    @Autowired
    RefundServiceImpl service;

    /**
    * 退款
    */
    @Cashier
    @PatchMapping(WebMvcRouter.ID)
    public AjaxResult refund(@PathVariable Long id, @RequestBody RefundForm form) {
        return service.refund(id, form);
    }
}
