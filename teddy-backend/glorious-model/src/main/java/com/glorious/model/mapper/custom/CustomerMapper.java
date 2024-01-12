package com.glorious.model.mapper.custom;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.glorious.model.entity.base.Supplier;
import com.glorious.model.entity.custom.Customer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerMapper extends BaseMapper<Customer> {

    // 查询最后一个
    Customer last();
}
