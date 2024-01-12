package com.glorious.system.auth.service.implView;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.glorious.model.entity.sys.User;
import com.glorious.model.mapperMulti.sys.UserDtoMapper;
import com.glorious.model.param.BaseParam;
import com.glorious.model.vo.sys.UserView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class UserServiceImplView {

    @Autowired
    UserDtoMapper userDtoMapper;

    /**
    * UserViews by QueryWrapper
    *
    * @params
    * @return
    */
    public List<UserView> userViews(QueryWrapper<User> queryWrapper) {
        return UserView.byDtoList(userDtoMapper.multiDeep(queryWrapper));
    }
}
