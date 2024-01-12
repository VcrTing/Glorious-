package com.glorious.system.order.controller;

import com.glorious.common.anno.Cashier;
import com.glorious.common.mvc.AjaxResult;
import com.glorious.common.define.webmvc.WebMvcRouter;
import com.glorious.model.define.enums.EnumOrderStatus;
import com.glorious.model.entity.order.Order;
import com.glorious.model.entity.product.Label;
import com.glorious.model.param.BaseParam;
import com.glorious.system.order.service.impl.OrderServiceImpl;
import com.glorious.system.order.service.implExtra.OrderServiceImplExtra;
import com.glorious.system.order.webmvc.OrderWebDefine;
import com.glorious.utils.utils.basic.QTypeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping(OrderWebDefine.STATUS)
public class OrderStatusController {

    @Autowired
    OrderServiceImplExtra serviceImplExtra;

    /**
     * 修改
     */
    @Cashier
    @PatchMapping(WebMvcRouter.ID)
    public AjaxResult upd(@PathVariable Long id, @RequestBody HashMap<String, String> statusMap) {
        return serviceImplExtra.updStatus(id, statusMap.get("status"));
    }
}
