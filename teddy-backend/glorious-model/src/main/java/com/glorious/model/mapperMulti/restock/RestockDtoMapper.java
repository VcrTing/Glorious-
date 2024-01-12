package com.glorious.model.mapperMulti.restock;

import com.glorious.model.dto.restock.RestockDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RestockDtoMapper {

    List<RestockDto> byProduct(@Param("pid") Long productID);
}
