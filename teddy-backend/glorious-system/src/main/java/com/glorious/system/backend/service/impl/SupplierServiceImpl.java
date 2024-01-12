package com.glorious.system.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.glorious.common.mvc.AjaxResult;
import com.glorious.common.utils.PageHelperUtil;
import com.glorious.framework.module.dataset.BackendCacheService;
import com.glorious.framework.component.sender.CacheQueueSender;
import com.glorious.model.entity.base.Supplier;
import com.glorious.model.mapper.base.SupplierMapper;
import com.glorious.model.param.BaseParam;
import com.glorious.system.backend.service.SupplierService;
import com.glorious.utils.utils.basic.QTypeUtil;
import com.glorious.utils.utils.bean.QBeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SupplierServiceImpl extends ServiceImpl<SupplierMapper, Supplier> implements SupplierService {

    @Autowired
    BackendCacheService backendCacheService;

    @Autowired
    CacheQueueSender cacheQueueSender;

    /**
    * 分页查询
    */
    public <T> AjaxResult pag(T param) {
        BaseParam baseParam = (BaseParam) param;

        LambdaQueryWrapper<Supplier> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderBy(baseParam.hasSort(), baseParam.isAsc(), Supplier::getId);

        if (baseParam.hasSearch()) {
            queryWrapper.like(Supplier::getEmail, baseParam.getSearch()).or();
            queryWrapper.like(Supplier::getPhone_no, baseParam.getSearch()).or();
            queryWrapper.like(Supplier::getSupplier_id, baseParam.getSearch()).or();
            queryWrapper.like(Supplier::getContact_person, baseParam.getSearch()).or();
        }

        PageHelperUtil.start(baseParam);
        return PageHelperUtil.successResult( this.list(queryWrapper), baseParam );
    }

    /**
    * 单个
    */
    @Override
    public AjaxResult one(Long id) {
        if (!QTypeUtil.isLong(id)) return AjaxResult.error("供應商 id 異常");
        // 优先从 缓存中 拿数据
        Supplier entity = backendCacheService.getSupplier(id);
        return AjaxResult.success(entity);
    }

    /**
    * 新增
    */
    public <T> AjaxResult pos(T data) {
        Supplier entity = QBeanUtil.convert(data, Supplier.class);
        if (entity == null) return AjaxResult.error("新增供應商表單數據格式錯誤。");
        boolean isOK = this.save(entity);
        // 更新 缓存
        cacheQueueSender.refreshSupplierList(); // backendCacheService.refreshSupplierList();
        return AjaxResult.restfull(isOK, entity);
    }

    /**
    * 修改
    */
    public <T> AjaxResult upd(Long id, T data) {
        if (!QTypeUtil.isLong(id)) return AjaxResult.error("供應商 ID 異常");

        Supplier entity = QBeanUtil.convert(data, Supplier.class);
        if (entity == null) return AjaxResult.error("修改供應商表單數據格式錯誤。");
        entity.setId(id);
        boolean isOK = updateById(entity);
        // 更新 缓存
        cacheQueueSender.refreshSupplierList(); // backendCacheService.refreshSupplierList();
        return AjaxResult.restfull(isOK, entity);
    }
}
