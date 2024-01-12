package com.glorious.model.mapper.restock;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.glorious.model.entity.restock.Invoice;
import com.glorious.model.entity.restock.Restock;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RestockMapper extends BaseMapper<Restock> {
}
