package com.glorious.model.mapperMulti.product;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.glorious.model.dto.product.ProductOfVariationAndStorehouseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductOfVariationAndStorehouseDtoMapper extends BaseMapper<ProductOfVariationAndStorehouseDto> {

    <T> List<ProductOfVariationAndStorehouseDto> multiDeep(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
    List<ProductOfVariationAndStorehouseDto> multiDeepByProduct(@Param("pid") Long productID);

    <T> List<ProductOfVariationAndStorehouseDto> excelDeep(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
}
