package com.glorious.framework.module.dataset;

import com.glorious.common.define.datatset.RedisConstants;
import com.glorious.framework.component.tools.RedisTool;
import com.glorious.model.entity.sys.User;
import com.glorious.model.mapper.sys.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestCacheService {

    @Autowired
    RedisTool redisTool;

    /**
    *
    */
    public void testSync(Object key) {
        synchronized (redisTool) {

            redisTool.test(key);
        }
    }
}
