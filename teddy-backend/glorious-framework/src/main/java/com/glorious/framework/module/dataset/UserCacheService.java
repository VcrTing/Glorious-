package com.glorious.framework.module.dataset;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.glorious.common.define.datatset.RedisConstants;
import com.glorious.framework.component.tools.RedisTool;
import com.glorious.model.entity.product.Label;
import com.glorious.model.entity.product.Product;
import com.glorious.model.entity.sys.User;
import com.glorious.model.mapper.product.LabelMapper;
import com.glorious.model.mapper.product.ProductMapper;
import com.glorious.model.mapper.sys.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserCacheService {

    @Autowired
    RedisTool redisTool;

    @Autowired
    UserMapper userMapper;

    // 存活時間是 7 天
    final static int LIVE_MINUTE = 24 * 60 * 7;

    /**
     * 获取 单个 用户
     */
    public User getUser(Long id) {
        User res = redisTool.getObject(RedisConstants.KEY_USER + id);
        if (res == null) {
            res = userMapper.selectById(id);
            setUser(res);
        }
        return res;
    }
    /**
     * 设置 单个 用户
     */
    public User setUser(User user) {
        redisTool.setObject(RedisConstants.KEY_USER + user.getId(), user, redisTool.randomMinute(LIVE_MINUTE));
        System.out.println("setUser");
        return user;
    }

}
