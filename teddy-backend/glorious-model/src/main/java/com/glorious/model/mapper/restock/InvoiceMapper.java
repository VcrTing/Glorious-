package com.glorious.model.mapper.restock;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.glorious.model.entity.restock.Broken;
import com.glorious.model.entity.restock.Invoice;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InvoiceMapper extends BaseMapper<Invoice> {
}
