package com.glorious.system.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.glorious.common.mvc.AjaxResult;
import com.glorious.model.entity.product.Variation;
import com.glorious.model.form.product.VariationForm;
import com.glorious.model.mapper.product.VariationMapper;
import com.glorious.system.product.service.ProductService;
import com.glorious.utils.utils.basic.QTypeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class VariationServiceImpl extends ServiceImpl<VariationMapper, Variation> implements ProductService {

    @Autowired
    ProductOfVariationAndStorehouseServiceImpl productOfVariationAndStorehouseService;

    @Override
    public <T> AjaxResult pag(T param) {
        return null;
    }

    @Override
    public AjaxResult one(Long id) {
        return null;
    }

    @Override
    public <T> AjaxResult pos(T data) {
        VariationForm form = (VariationForm) data;
        if (!QTypeUtil.isLong(form.getProduct())) return AjaxResult.error("產品 ID 異常");
        Variation entity = new Variation(form.getName());
        productOfVariationAndStorehouseService.connectionVariationForProduct(form.getProduct(), entity);
        return AjaxResult.restfull(QTypeUtil.isLong(entity.getId()), entity);
    }

    @Override
    public <T> AjaxResult upd(Long id, T data) {
        VariationForm form = (VariationForm) data;
        if (!QTypeUtil.isLong(id)) return AjaxResult.error("樣式 ID 異常");
        Variation entity = new Variation(form.getName());
        entity.setId(id);
        return AjaxResult.restfull(this.updateById(entity), entity);
    }
}
