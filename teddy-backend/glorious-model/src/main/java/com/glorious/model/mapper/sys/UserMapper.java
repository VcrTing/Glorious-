package com.glorious.model.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.glorious.model.entity.sys.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
