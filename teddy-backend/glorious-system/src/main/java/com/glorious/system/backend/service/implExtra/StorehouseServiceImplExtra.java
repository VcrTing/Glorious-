package com.glorious.system.backend.service.implExtra;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.glorious.framework.module.dataset.BackendCacheService;
import com.glorious.model.entity.base.Storehouse;
import com.glorious.model.mapper.base.StorehouseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class StorehouseServiceImplExtra extends ServiceImpl<StorehouseMapper, Storehouse> {

    @Autowired
    BackendCacheService backendCacheService;

    /**
    * 查询最后一个
    */
    public Optional<Storehouse> last() {
        /*
        QueryWrapper<Storehouse> queryWrapper = new QueryWrapper<>();
        queryWrapper.last("LIMIT 1");
        return this.list(queryWrapper).stream().findFirst();
         */
        /*
        return Optional.ofNullable(
                this.lambdaQuery().orderBy(true,false, Storehouse::getId)
                        .last("LIMIT 1").one()
        );
         */
        return backendCacheService.getStorehouseList().stream().findFirst();
    }
}
