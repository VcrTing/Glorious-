package com.glorious.model.mapperMulti.restock;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.glorious.model.dto.restock.InvoiceDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InvoiceDtoMapper extends BaseMapper<InvoiceDto> {

    InvoiceDto oneDeep(@Param("id") Long id);

    <T> List<InvoiceDto> multiDeep(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
}
