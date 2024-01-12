package com.glorious.system.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.glorious.common.mvc.AjaxResult;
import com.glorious.common.utils.PageHelperUtil;
import com.glorious.framework.module.dataset.ProductCacheService;
import com.glorious.framework.component.sender.CacheQueueSender;
import com.glorious.model.entity.product.Label;
import com.glorious.model.form.product.LabelForm;
import com.glorious.model.mapper.product.LabelMapper;
import com.glorious.model.param.BaseParam;
import com.glorious.model.vo.product.LabelView;
import com.glorious.system.product.service.LabelService;
import com.glorious.system.product.service.implView.LabelServiceImplView;
import com.glorious.utils.utils.basic.QTypeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class LabelServiceImpl extends ServiceImpl<LabelMapper, Label> implements LabelService {

    @Autowired
    LabelServiceImplView serviceImplView;

    @Autowired
    ProductCacheService productCacheService;

    @Autowired
    CacheQueueSender cacheQueueSender;

    @Override
    public <T> AjaxResult pag(T param) {
        BaseParam baseParam = (BaseParam) param;

        LambdaQueryWrapper<Label> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderBy(baseParam.hasSort(), baseParam.isAsc(), Label::getId);

        // 优先从 缓存中 拿数据
        List<Label> list = productCacheService.getLabelListCache();
        if (PageHelperUtil.isNotInMinSize(list)) {
            PageHelperUtil.start(baseParam);
            list = this.list( queryWrapper );
        }
        return PageHelperUtil.successResult(LabelView.byEntities( list ), baseParam);

    }

    @Override
    public AjaxResult one(Long id) {
        return AjaxResult.restfull(QTypeUtil.isLong(id), serviceImplView.labelAndProductView(id));
    }

    @Override
    public <T> AjaxResult pos(T data) {
        Label entity = LabelForm.toEntity((LabelForm) data).orElse(null);
        log.debug("产品标签新增 entity = {}", entity);
        if (entity == null) return AjaxResult.error("產品標籤表單數據格式錯誤");
        boolean isOK = this.save(entity);
        // 更新 缓存
        cacheQueueSender.refreshLabelList(); // productCacheService.refreshLabelList();
        return AjaxResult.restfull(isOK, entity);
    }

    @Override
    public <T> AjaxResult upd(Long id, T data) {
        if (!QTypeUtil.isLong(id)) return AjaxResult.error("產品標籤 ID 異常");
        Label entity = LabelForm.toEntity((LabelForm) data).orElse(null);
        log.debug("产品标签修改 entity = {}", entity);
        if (entity == null) return AjaxResult.error("產品標籤表單數據格式錯誤");
        entity.setId(id);
        boolean isOK = this.updateById(entity);
        // 更新 缓存
        cacheQueueSender.refreshLabelList(); // productCacheService.refreshLabelList();
        return AjaxResult.restfull(isOK, entity);
    }

    @Override
    public <T> AjaxResult del(Long id) {
        boolean isOK = this.removeById(id);
        // 更新 缓存 数据
        cacheQueueSender.refreshLabelList(); // productCacheService.refreshLabelList();
        return AjaxResult.restfull(isOK, this.getById(id));
    }
}
