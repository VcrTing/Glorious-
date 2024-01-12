package com.glorious.system.product.service.implView;

import com.glorious.framework.module.dataset.ProductCacheService;
import com.glorious.model.mapper.product.LabelMapper;
import com.glorious.model.vo.product.LabelAndProductView;
import com.glorious.system.product.service.implExtra.ProductServiceImplExtra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LabelServiceImplView {

    @Autowired
    LabelMapper mapper;

    @Autowired
    ProductCacheService productCacheService;

    @Autowired
    ProductServiceImplExtra productServiceImplExtra;

    /**
    * Label 与 label 的 products
    */
    public LabelAndProductView labelAndProductView(Long lid) {
        return LabelAndProductView.byEntity(
                productCacheService.getLabel(lid),
                productServiceImplExtra.byLabelID(lid));
    }
}
