package com.glorious.system.cashier.controller;

import com.glorious.common.anno.Cashier;
import com.glorious.common.mvc.AjaxResult;
import com.glorious.model.form.order.CheckoutForm;
import com.glorious.system.cashier.service.CheckoutService;
import com.glorious.system.order.webmvc.OrderWebDefine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(OrderWebDefine.API)
public class CheckoutController {

    @Autowired
    CheckoutService checkoutService;

    @Cashier
    @PostMapping
    public AjaxResult checkout(@RequestBody CheckoutForm form) {
        return checkoutService.checkout(form);
    }
}
