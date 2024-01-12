package com.glorious.model.mapper.product;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.glorious.model.entity.order.Order;
import com.glorious.model.entity.product.Variation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VariationMapper extends BaseMapper<Variation> {

}
