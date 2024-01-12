package com.glorious.system.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.glorious.common.mvc.AjaxResult;
import com.glorious.common.utils.PageHelperUtil;
import com.glorious.model.dto.order.OrderDto;
import com.glorious.model.entity.order.Order;
import com.glorious.model.mapper.order.OrderMapper;
import com.glorious.model.mapperMulti.order.OrderDtoMapper;
import com.glorious.model.param.OrderParam;
import com.glorious.model.vo.order.OrderSimplyView;
import com.glorious.system.order.service.OrderService;
import com.glorious.system.order.service.implVIew.OrderServiceImplView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    OrderMapper mapper;

    @Autowired
    OrderDtoMapper dtoMapper;

    @Autowired
    OrderServiceImplView serviceImplView;

    /**
    * 分页查询
    */
    @Override
    public <T> AjaxResult pag(T param) {

        OrderParam orderParam = (OrderParam) param;

        PageHelperUtil.start(orderParam);
        List<OrderDto> dtoList = dtoMapper.multiDeep(orderParam.queryWrapper());

        log.debug("dtoList = " + dtoList);

        List<OrderSimplyView> simplyViewList = OrderSimplyView.byDTOs(dtoList);
        return PageHelperUtil.successResult( simplyViewList, orderParam );
    }

    /**
    * 查询一个
    */
    @Override
    public AjaxResult one(Long id) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("me.id", id);
        return serviceImplView.detail(queryWrapper);
    }

    /**
     * 查询一个
     */
    @Override
    public AjaxResult one(String uuid) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("me.order_id", uuid);
        return serviceImplView.detail(queryWrapper);
    }
}
