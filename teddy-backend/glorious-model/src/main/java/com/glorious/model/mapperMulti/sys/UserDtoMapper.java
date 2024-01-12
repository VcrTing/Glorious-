package com.glorious.model.mapperMulti.sys;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.glorious.model.dto.sys.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDtoMapper extends BaseMapper<UserDto> {

    // <T, P extends IPage<T>> List<UserDto> multiDeep(P page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
    <T> List<UserDto> multiDeep(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
}
