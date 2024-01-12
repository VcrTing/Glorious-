package com.glorious.system.cashier.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.glorious.common.mvc.AjaxResult;
import com.glorious.common.utils.PageHelperUtil;
import com.glorious.model.dto.order.OrderDto;
import com.glorious.model.entity.order.Order;
import com.glorious.model.mapper.order.OrderMapper;
import com.glorious.model.mapperMulti.order.OrderDtoMapper;
import com.glorious.model.param.CashierOrderParam;
import com.glorious.model.vo.order.OrderSimplyView;
import com.glorious.system.order.service.implVIew.OrderServiceImplView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CashierOrderServiceImpl extends ServiceImpl<OrderMapper, Order> {

    @Autowired
    OrderDtoMapper dtoMapper;

    @Autowired
    OrderServiceImplView serviceImplView;

    /**
     * 分页查询
     */
    public <T> AjaxResult pag(T param) {
        CashierOrderParam cashierOrderParam = (CashierOrderParam) param;
        PageHelperUtil.start(cashierOrderParam);
        List<OrderDto> dtoList = dtoMapper.multiDeep(cashierOrderParam.queryWrapper());
        return PageHelperUtil.successResult( OrderSimplyView.byDTOs(dtoList), cashierOrderParam );
    }


    /**
     * 查询一个
     */
    public AjaxResult one(Long id) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("me.id", id);
        return serviceImplView.detail(queryWrapper);
    }
}
