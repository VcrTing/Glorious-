package com.glorious.system.product.service.implExtra;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.glorious.framework.module.dataset.ProductCacheService;
import com.glorious.model.define.converter.LabelIdConverter;
import com.glorious.model.entity.base.Supplier;
import com.glorious.model.entity.product.Product;
import com.glorious.model.entity.restock.Restock;
import com.glorious.model.form.restock.invoice.InvoiceFormRestocks;
import com.glorious.model.mapper.base.SupplierMapper;
import com.glorious.model.mapper.product.LabelMapper;
import com.glorious.model.mapper.product.ProductMapper;
import com.glorious.system.restock.service.implExtra.RestockServiceImplExtra;
import com.glorious.utils.utils.basic.QSumUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class ProductServiceImplExtra extends ServiceImpl<ProductMapper, Product> {

    @Autowired
    ProductMapper productMapper;

    @Autowired
    LabelMapper labelMapper;

    @Autowired
    SupplierMapper supplierMapper;

    @Autowired
    RestockServiceImplExtra restockServiceImplExtra;

    @Autowired
    ProductCacheService productCacheService;

    /**
    * 查询许多产品，包括产品 ID
    */
    public List<Product> byLabelID(Long lid) {
        LambdaQueryWrapper<Product> qw = new LambdaQueryWrapper<>();
        qw.like(Product::getLabels, LabelIdConverter.genStringId(lid));
        return this.list(qw);
    }

    /**
    * 修改产品的 最新加货信息
    */
    public Boolean updRestockMsg(InvoiceFormRestocks restock, Supplier supplier) {

        LambdaUpdateWrapper<Product> updateWrapper = new LambdaUpdateWrapper<>();

        updateWrapper
                .eq(Product::getId, restock.getProduct())
                .set(Product::getNew_restock_date, new Date())
                .set(Product::getNew_supplier, supplier.getName())
                .set(Product::getNew_supplier_sql_id, supplier.getId());

        if (restock.getLowest_price() != null) {
            updateWrapper.set(Product::getNew_lowest_price, restock.getLowest_price());
        }
        if (restock.getSelling_price() != null) {
            updateWrapper.set(Product::getNew_selling_price, restock.getSelling_price());
        }
        if (restock.getNet_price() != null) {
            updateWrapper.set(Product::getNew_restock_price, restock.getNet_price());
        }

        return this.update(updateWrapper);
    }

    /**
    * 修改入货平均价
    */
    public Boolean updAverageRestockPrice(Long pid, BigDecimal price) {

        // 所有入货记录
        List<Restock> restocks = restockServiceImplExtra.byProduct(pid);

        BigDecimal totalPrice = BigDecimal.ZERO;
        int totalNum = 1;

        for (Restock r: restocks) {
            totalPrice = QSumUtil.add(totalPrice, r.getRestock_price());
            totalNum = QSumUtil.add(totalNum, 1);
        }

        // 增加 新 平均价
        totalPrice = QSumUtil.add(totalPrice, price);

        // 计算 平均价
        BigDecimal average = totalPrice.divide(BigDecimal.valueOf(totalNum), BigDecimal.ROUND_HALF_UP);

        // 修改 产品 入货平均价
        return this.lambdaUpdate()
                .eq(Product::getId, pid)
                .set(Product::getAverage_restock_price, average)
                .update();
    }

}
