package com.glorious.system.cashier.controller;

import com.glorious.common.anno.Cashier;
import com.glorious.common.define.webmvc.WebMvcRouter;
import com.glorious.common.mvc.AjaxResult;
import com.glorious.model.param.BaseParam;
import com.glorious.model.param.CashierOrderParam;
import com.glorious.model.param.OrderParam;
import com.glorious.system.backend.webmvc.CustomerWebDefine;
import com.glorious.system.cashier.service.impl.CashierOrderServiceImpl;
import com.glorious.system.cashier.webmvc.CashierOrderDefine;
import com.glorious.utils.utils.basic.QTypeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CashierOrderDefine.API)
public class CashierOrderController {

    @Autowired
    CashierOrderServiceImpl service;

    /**
     * 分页 查询
     */
    @Cashier
    @GetMapping
    public AjaxResult pag(CashierOrderParam param) {
        return service.pag(param); }

    /**
     * 单个
     */
    @Cashier
    @GetMapping(WebMvcRouter.ID)
    public AjaxResult one(@PathVariable Long id) {
        return service.one(id);
    }
}
