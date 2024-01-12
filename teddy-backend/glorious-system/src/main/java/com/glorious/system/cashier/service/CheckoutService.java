package com.glorious.system.cashier.service;

import com.glorious.common.mvc.AjaxResult;
import com.glorious.model.entity.base.Storehouse;
import com.glorious.model.entity.custom.Customer;
import com.glorious.model.entity.custom.CustomerLevel;
import com.glorious.model.entity.sys.User;
import com.glorious.model.form.order.CheckoutForm;

public interface CheckoutService {

    /**
    * 扣除库存
    */
    Object deductInventory(CheckoutForm form, Long storehouseID);

    /**
    * 新增订单，正式结算
    */
    Object posOrder(CheckoutForm form, User cashier, Customer customer);

    /**
    * 结算方法
    */
    AjaxResult checkout(CheckoutForm form);
}
