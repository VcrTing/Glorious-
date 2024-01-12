package com.glorious.system.cashier.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.glorious.common.mvc.AjaxResult;
import com.glorious.common.utils.PageHelperUtil;
import com.glorious.model.entity.product.Product;
import com.glorious.model.mapper.product.ProductMapper;
import com.glorious.model.param.CashierProductParam;
import com.glorious.system.product.service.implView.ProductServiceImplView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CashierProductServiceImpl extends ServiceImpl<ProductMapper, Product> {

    @Autowired
    ProductServiceImplView serviceImplView;

    /**
     * 分页 列表
     */
    public <T> AjaxResult pag(T param) {
        CashierProductParam productParam = (CashierProductParam) param;
        PageHelperUtil.start(productParam);
        List<Product> products = this.list(productParam.genQueryWrapper());
        return PageHelperUtil.successResult(serviceImplView.multiCashier(products), productParam );
    }

}
