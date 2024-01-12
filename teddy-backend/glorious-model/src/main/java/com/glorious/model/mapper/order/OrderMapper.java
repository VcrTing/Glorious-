package com.glorious.model.mapper.order;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.glorious.model.dto.sys.UserDto;
import com.glorious.model.entity.custom.CustomerLevel;
import com.glorious.model.entity.order.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    <T> List<Order> multiDeep(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
}
