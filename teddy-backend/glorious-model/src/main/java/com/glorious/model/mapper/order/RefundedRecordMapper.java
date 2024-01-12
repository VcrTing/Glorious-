package com.glorious.model.mapper.order;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.glorious.model.entity.order.Order;
import com.glorious.model.entity.order.RefundedRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RefundedRecordMapper extends BaseMapper<RefundedRecord> {
}
