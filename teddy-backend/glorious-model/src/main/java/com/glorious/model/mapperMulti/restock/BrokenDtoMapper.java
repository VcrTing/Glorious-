package com.glorious.model.mapperMulti.restock;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.glorious.model.dto.restock.BrokenDto;
import com.glorious.model.dto.restock.InvoiceDto;
import com.glorious.model.entity.restock.Broken;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BrokenDtoMapper extends BaseMapper<BrokenDto> {

    <T> List<BrokenDto> multiDeep(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
}
