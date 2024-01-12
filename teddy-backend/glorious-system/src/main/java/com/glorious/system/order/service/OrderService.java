package com.glorious.system.order.service;


import com.glorious.common.mvc.AjaxResult;
import com.glorious.system.QueryService;

public interface OrderService extends QueryService {

    /**
     * 单个
     */
    AjaxResult one(String uuid);
}
