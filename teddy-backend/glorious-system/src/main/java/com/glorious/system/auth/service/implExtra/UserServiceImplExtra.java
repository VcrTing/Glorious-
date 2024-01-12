package com.glorious.system.auth.service.implExtra;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.glorious.common.mvc.AjaxResult;
import com.glorious.model.entity.sys.User;
import com.glorious.model.mapper.sys.UserMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class UserServiceImplExtra extends ServiceImpl<UserMapper, User> {

    /**
     * 通过 电邮地址 查询 用户
     * @params
     * @return
     */
    public User byEmail(String email) {
        if (!StringUtils.hasText(email)) return null;
        return this.lambdaQuery().eq(User::getEmail, email).one();
    }

    /**
    * 检查 相同 用户
    * @params
    * @return
    */
    public Boolean isSameUser(User entity) {
        return entity != null && this.byEmail(entity.getEmail()) == null;
    }
}
