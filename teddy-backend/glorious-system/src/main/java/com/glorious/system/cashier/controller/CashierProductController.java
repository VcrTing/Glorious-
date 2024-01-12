package com.glorious.system.cashier.controller;

import com.glorious.common.anno.Admin;
import com.glorious.common.anno.Cashier;
import com.glorious.common.mvc.AjaxResult;
import com.glorious.model.param.CashierProductParam;
import com.glorious.system.cashier.service.impl.CashierOrderServiceImpl;
import com.glorious.system.cashier.service.impl.CashierProductServiceImpl;
import com.glorious.system.cashier.webmvc.CashierProductDefine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CashierProductDefine.API)
public class CashierProductController {

    @Autowired
    CashierProductServiceImpl service;

    /**
     * 分页 查询
     */
    // @PreAuthorize("@Access.cashier()")
    @Cashier
    @GetMapping
    public AjaxResult pag(CashierProductParam param) { return service.pag(param); }

}
