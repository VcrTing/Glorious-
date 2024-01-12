package com.glorious.system.order.controller;

import com.glorious.common.anno.Admin;
import com.glorious.common.define.webmvc.WebMvcRouter;
import com.glorious.common.mvc.AjaxResult;
import com.glorious.model.param.OrderParam;
import com.glorious.model.param.ProfitParam;
import com.glorious.system.order.service.impl.OrderServiceImpl;
import com.glorious.system.order.service.implVIew.ProfitServiceImplView;
import com.glorious.system.order.webmvc.OrderWebDefine;
import com.glorious.system.order.webmvc.ProfitWebDefine;
import com.glorious.utils.utils.basic.QTypeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ProfitWebDefine.API)
public class ProfitController {

    @Autowired
    ProfitServiceImplView serviceImplView;

    /**
     * 分页 查询
     */
    @Admin
    @GetMapping
    public AjaxResult pag(ProfitParam param) { return serviceImplView.multiDeep(param); }
}
