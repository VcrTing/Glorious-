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
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    LabelMapper labelMapper;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    ProductServiceImplView serviceImplView;

    @Autowired
    ProductOfVariationAndStorehouseServiceImpl productOfVariationAndStorehouseService;

    /**
     * 分页 列表
     */
    @Override
    public <T> AjaxResult pag(T param) {
        ProductParam productParam = (ProductParam) param;
        List<Product> products = this.list(productParam.genQueryWrapper());
        PageHelperUtil.start(productParam);
        return PageHelperUtil.successResult(serviceImplView.multiSimply(products), productParam );
    }

    /**
    * 单个
    */
    public AjaxResult one(String product_id) {
        if (!StringUtils.hasText(product_id)) return AjaxResult.error("產品 ID 異常");
        Product entity = this.lambdaQuery().eq(Product::getProduct_id, product_id).one();
        if (entity == null) return AjaxResult.error("未找到該產品");
        return AjaxResult.success( serviceImplView.detail(entity) );
    }

    @Override
    public AjaxResult one(Long productID) {
        if (!QTypeUtil.isLong(productID)) return AjaxResult.error("產品 ID 異常");
        Product entity = this.getById(productID);
        if (entity == null) return AjaxResult.error("未找到該產品");
        return AjaxResult.success( serviceImplView.detail(entity) );
    }

    /**
    * 新增
    */
    @Override
    public <T> AjaxResult pos(T data) {
        Product entity = ProductPostForm.toEntity((ProductPostForm) data).orElse(null);
        if (entity == null) return AjaxResult.error("產品表單數據格式錯誤");
        if (this.save(entity)) {
            // 为 产品 添加 默认的 样式，名字在 VARIATION_NAME_DEF 里
            productOfVariationAndStorehouseService.connectionVariationForProduct(
                    entity.getId(),
                    new Variation(DatasetEntityConstants.VARIATION_NAME_DEF));
        }
        return AjaxResult.restfull(entity.getId() != null, entity);
    }

    /**
     * 修改
     */
    @Override
    public <T> AjaxResult upd(Long id, T data) {
        if (!QTypeUtil.isLong(id)) return AjaxResult.error("產品 ID 異常");
        ProductPatchForm patchForm = (ProductPatchForm) data;
        return AjaxResult.restfull(this.update(patchForm.updateWrapper(id)), this.getById(id));
    }
}
