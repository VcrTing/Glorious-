package com.glorious.model.mapperMulti.order;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.glorious.model.dto.order.OrderDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderDtoMapper extends BaseMapper<OrderDto> {

    // OrderDto oneDeep(@Param("id") Long id);

    <T> List<OrderDto> multiDeep(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
}
