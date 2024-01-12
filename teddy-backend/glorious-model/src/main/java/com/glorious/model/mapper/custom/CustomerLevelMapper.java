package com.glorious.model.mapper.custom;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.glorious.model.entity.custom.Customer;
import com.glorious.model.entity.custom.CustomerLevel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerLevelMapper extends BaseMapper<CustomerLevel> {
}
