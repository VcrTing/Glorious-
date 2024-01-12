package com.glorious.system.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.glorious.common.mvc.AjaxResult;
import com.glorious.common.utils.PageHelperUtil;
import com.glorious.model.define.dataset.DatasetEntityConstants;
import com.glorious.model.entity.product.Product;
import com.glorious.model.entity.product.Variation;
import com.glorious.model.form.product.ProductPatchForm;
import com.glorious.model.form.product.ProductPostForm;
import com.glorious.model.mapper.product.LabelMapper;
import com.glorious.model.mapper.product.ProductMapper;
import com.glorious.model.param.ProductParam;
import com.glorious.system.product.service.ProductFileService;
import com.glorious.system.product.service.ProductService;
import com.glorious.system.product.service.implView.ProductServiceImplView;
import com.glorious.utils.utils.basic.QTypeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Slf4j
@Service
public class ProductFileServiceImpl implements ProductFileService {

    @Override
    public AjaxResult getHotSaleExcel() {
        return null;
    }

    @Override
    public AjaxResult getLessInventoryExcel() {
        return null;
    }
}
