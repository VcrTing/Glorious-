package com.glorious.model.mapper.product;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.glorious.model.entity.product.ProductOfVariationAndStorehouse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductOfVariationAndStorehouseMapper extends BaseMapper<ProductOfVariationAndStorehouse> {

    <T> List<ProductOfVariationAndStorehouse> multiDeep(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
}
