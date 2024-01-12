package com.glorious.system.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.glorious.common.mvc.AjaxResult;
import com.glorious.common.utils.PageHelperUtil;
import com.glorious.framework.module.dataset.BackendCacheService;
import com.glorious.framework.component.sender.CacheQueueSender;
import com.glorious.model.entity.base.Storehouse;
import com.glorious.model.mapper.base.StorehouseMapper;
import com.glorious.model.param.BaseParam;
import com.glorious.system.backend.service.SupplierService;
import com.glorious.utils.utils.basic.QTypeUtil;
import com.glorious.utils.utils.bean.QBeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class StorehouseServiceImpl extends ServiceImpl<StorehouseMapper, Storehouse> implements SupplierService {

    @Autowired
    BackendCacheService backendCacheService;

    @Autowired
    CacheQueueSender cacheQueueSender;

    @Override
    public <T> AjaxResult pag(T param) {
        BaseParam baseParam = (BaseParam) param;

        LambdaQueryWrapper<Storehouse> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderBy(baseParam.hasSort(), baseParam.isAsc(), Storehouse::getId);

        // 优先从 缓存中 拿数据
        List<Storehouse> storehouseList = backendCacheService.getStorehouseList();
        if (PageHelperUtil.isNotInMinSize(storehouseList)) {
            PageHelperUtil.start(baseParam);
            storehouseList = this.list( queryWrapper );
        }
        return PageHelperUtil.successResult( storehouseList, baseParam );
    }

    @Override
    public AjaxResult one(Long id) {
        if (!QTypeUtil.isLong(id)) return AjaxResult.error("倉庫 id 異常。");
        // 优先从 缓存中 拿数据
        Storehouse entity = backendCacheService.getStorehouse(id);
        return AjaxResult.success(entity);
    }

    @Override
    public <T> AjaxResult pos(T data) {
        Storehouse entity = QBeanUtil.convert(data, Storehouse.class);
        if (entity == null) return AjaxResult.error("倉庫數據格式錯誤。");
        boolean isOK = this.save(entity);
        // 更新 缓存
        cacheQueueSender.refreshStorehouseList(); // backendCacheService.refreshStorehouseList();
        return AjaxResult.restfull(isOK, entity);
    }

    @Override
    public <T> AjaxResult upd(Long id, T data) {
        if (!QTypeUtil.isLong(id)) return AjaxResult.error("倉庫 id 異常。");
        Storehouse entity = QBeanUtil.convert(data, Storehouse.class);
        if (entity == null) return AjaxResult.error("倉庫數據格式錯誤。");
        entity.setId(id);
        boolean isOK = updateById(entity);
        // 更新 缓存
        cacheQueueSender.refreshStorehouseList(); // backendCacheService.refreshStorehouseList();
        return AjaxResult.restfull(isOK, entity);
    }
}
