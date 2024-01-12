package com.glorious.system.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.glorious.common.mvc.AjaxResult;
import com.glorious.common.utils.PageHelperUtil;
import com.glorious.framework.module.dataset.BackendCacheService;
import com.glorious.framework.component.sender.CacheQueueSender;
import com.glorious.model.entity.custom.CustomerLevel;
import com.glorious.model.form.custom.CustomerLevelForm;
import com.glorious.model.mapper.custom.CustomerLevelMapper;
import com.glorious.model.param.BaseParam;
import com.glorious.model.vo.custom.CustomerLevelView;
import com.glorious.system.backend.service.CustomerLevelService;
import com.glorious.utils.utils.basic.QTypeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CustomerLevelServiceImpl extends ServiceImpl<CustomerLevelMapper, CustomerLevel> implements CustomerLevelService {

    @Autowired
    BackendCacheService backendCacheService;

    @Autowired
    CacheQueueSender cacheQueueSender;

    /**
    * 分页查询
    */
    public <T> AjaxResult pag(T param) {
        BaseParam baseParam = (BaseParam) param;

        LambdaQueryWrapper<CustomerLevel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderBy(baseParam.hasSort(), baseParam.isAsc(), CustomerLevel::getId);

        // 优先从 缓存中 拿数据
        List<CustomerLevel> customerLevelList = backendCacheService.getCustomerLevelList();
        if (PageHelperUtil.isNotInMinSize(customerLevelList)) {
            PageHelperUtil.start(baseParam);
            customerLevelList = this.list( queryWrapper );
        }
        return PageHelperUtil.successResult( CustomerLevelView.byEntities( customerLevelList ), baseParam );
    }

    /**
    * 单个
    */
    @Override
    public AjaxResult one(Long id) {
        if (!QTypeUtil.isLong(id)) return AjaxResult.error("用戶等級 id 異常");
        // 先从缓存中 获取数据
        CustomerLevel entity = backendCacheService.getCustomerLevel(id);
        return AjaxResult.success(CustomerLevelView.byEntity(entity));
    }

    /**
    * 新增
    */
    public <T> AjaxResult pos(T data) {
        CustomerLevel entity = CustomerLevelForm.toEntity((CustomerLevelForm) data).orElse(null);
        if (entity == null) return AjaxResult.error("用戶等級 數據格式錯誤。");
        boolean isOK = this.save(entity);
        // 更新 缓存 数据
        cacheQueueSender.refreshCustomerLevelList(); // backendCacheService.refreshCustomerLevelList();
        return AjaxResult.restfull(isOK, CustomerLevelView.byEntity(entity));
    }

    /**
    * 修改
    */
    public <T> AjaxResult upd(Long id, T data) {
        if (!QTypeUtil.isLong(id)) return AjaxResult.error("用戶等級 ID 異常");
        CustomerLevel entity = CustomerLevelForm.toEntity((CustomerLevelForm) data).orElse(null);
        if (entity == null) return AjaxResult.error("用戶等級 數據格式錯誤。");
        entity.setId(id);
        boolean isOK = this.updateById(entity);
        // 更新 缓存 数据
        cacheQueueSender.refreshCustomerLevelList(); // backendCacheService.refreshCustomerLevelList();
        return AjaxResult.restfull(isOK, CustomerLevelView.byEntity(entity));
    }

    /**
    * 删除
    */
    @Override
    public <T> AjaxResult del(Long id) {
        boolean isOK = this.removeById(id);
        // 更新 缓存 数据
        cacheQueueSender.refreshCustomerLevelList(); // backendCacheService.refreshCustomerLevelList();
        return AjaxResult.restfull(isOK, this.getById(id));
    }
}
