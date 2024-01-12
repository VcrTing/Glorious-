package com.glorious.system.product.service.implView;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.glorious.framework.module.dataset.ProductCacheService;
import com.glorious.model.dto.product.ProductOfVariationAndStorehouseDto;
import com.glorious.model.dto.restock.RestockDto;
import com.glorious.model.entity.product.Label;
import com.glorious.model.entity.product.Product;
import com.glorious.model.entity.restock.Broken;
import com.glorious.model.entity.restock.Invoice;
import com.glorious.model.entity.restock.Restock;
import com.glorious.model.mapper.product.LabelMapper;
import com.glorious.model.mapper.product.ProductMapper;
import com.glorious.model.mapper.restock.BrokenMapper;
import com.glorious.model.mapper.restock.InvoiceMapper;
import com.glorious.model.mapperMulti.product.ProductOfVariationAndStorehouseDtoMapper;
import com.glorious.model.mapperMulti.restock.RestockDtoMapper;
import com.glorious.model.transfer.ProductDetailTransfer;
import com.glorious.model.transfer.ProductSimplyTransfer;
import com.glorious.model.vo.cashier.CashierProductView;
import com.glorious.model.vo.product.ProductDetailView;
import com.glorious.model.vo.product.ProductSimplyView;
import com.glorious.system.restock.service.implExtra.BrokenServiceImplExtra;
import com.glorious.system.restock.service.implExtra.RestockServiceImplExtra;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ProductServiceImplView extends ServiceImpl<ProductMapper, Product> {

    @Autowired
    LabelMapper labelMapper;

    @Autowired
    InvoiceMapper invoiceMapper;

    @Autowired
    RestockDtoMapper restockDtoMapper;

    @Autowired
    BrokenServiceImplExtra brokenServiceImplExtra;

    @Autowired
    ProductOfVariationAndStorehouseDtoMapper productOfVariationAndStorehouseDtoMapper;

    @Autowired
    ProductCacheService productCacheService;

    /**
    * 列表
    */
    public List<ProductSimplyView> multiSimply(List<Product> products) {
        // 全部的 可用的 标签
        List<Label> allLabels = productCacheService.getLabelList(); // labelMapper.selectList(null);
        // 全部的 库存数据，浅层连表
        List<ProductOfVariationAndStorehouseDto> allPvsDto = productOfVariationAndStorehouseDtoMapper.multiDeep(null);

        return products.stream().map(product -> {
            // 本产品的 库存 DTO 数据
            List<ProductOfVariationAndStorehouseDto> pvsDto = ProductOfVariationAndStorehouseDto.filterByProduct(allPvsDto, product.getId());
            // 生成 视图 对象
            return ProductSimplyTransfer.genView(product, allLabels, pvsDto);
        }).collect(Collectors.toList());
    }

    /**
     * 单个
     */
    public ProductDetailView detail(Product entity) {

        // 全部的 可用的 标签
        List<Label> allLabels = productCacheService.getLabelList(); // labelMapper.selectList(null);
        // 全部的 库存数据，浅层连表
        List<ProductOfVariationAndStorehouseDto> allPvsDto = productOfVariationAndStorehouseDtoMapper.multiDeepByProduct(entity.getId());

        // 拿去 入货数据
        List<RestockDto> restocks = restockDtoMapper.byProduct(entity.getId());
        log.debug("restocks = {}", restocks);

        // 拿去 坏货数据
        List<Broken> brokenList = brokenServiceImplExtra.byProduct(entity.getId());

        return ProductDetailTransfer.genDetail(entity, allLabels, allPvsDto, restocks, brokenList);
    }

    /**
     * 收银员 产品列表
     */
    public List<CashierProductView> multiCashier(List<Product> products) {
        // 全部的 库存数据，浅层连表
        List<ProductOfVariationAndStorehouseDto> allPvsDto = productOfVariationAndStorehouseDtoMapper.multiDeep(null);

        return products.stream().map(product -> {
            // 本产品的 库存 DTO 数据
            List<ProductOfVariationAndStorehouseDto> pvsDto = ProductOfVariationAndStorehouseDto.filterByProduct(allPvsDto, product.getId());
            // 生成 视图 对象
            CashierProductView view = CashierProductView.byEntity(product);
            // 加入其他数据
            view.setVariations(ProductDetailTransfer.genVariations(product, pvsDto));
            view.setStorehouse_info(ProductDetailTransfer.genStorehouses(pvsDto));
            // 返回
            return view;
        }).collect(Collectors.toList());
    }
}
