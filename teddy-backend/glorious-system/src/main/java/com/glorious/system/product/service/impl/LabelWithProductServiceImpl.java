package com.glorious.system.product.service.impl;

import com.glorious.common.mvc.AjaxResult;
import com.glorious.model.define.converter.LabelIdConverter;
import com.glorious.model.entity.product.Product;
import com.glorious.model.mapper.product.ProductMapper;
import com.glorious.system.product.service.LabelWithProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LabelWithProductServiceImpl implements LabelWithProductService {

    @Autowired
    ProductMapper productMapper;

    public AjaxResult operaProductLabel(Long pid, Long lid, boolean isAdd) {
        Product product = productMapper.selectById(pid);

        if (product != null) {
            LabelIdConverter idConverter = LabelIdConverter.init(product);
            // 删除 或 新增
            if (isAdd) { idConverter.add(lid); } else { idConverter.remove(lid); }
            product.setLabels(idConverter.getJson());
            // 修改产品
            return AjaxResult.restfull(productMapper.updateById(product) > 0, product);
        }
        return AjaxResult.error("產品 ID 異常");
    }

    @Override
    public AjaxResult addLabel(Long pid, Long lid) {
        return operaProductLabel(pid, lid, true);
    }

    @Override
    public AjaxResult delLabel(Long pid, Long lid) {
        return operaProductLabel(pid, lid, false);
    }
}
