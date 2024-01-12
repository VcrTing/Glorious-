package com.glorious.system.restock.service.implExtra;

import com.baomidou.mybatisplus.extension.api.R;
import com.glorious.common.exception.QLogicException;
import com.glorious.common.mvc.AjaxResult;
import com.glorious.framework.module.dataset.BackendCacheService;
import com.glorious.model.entity.base.Storehouse;
import com.glorious.model.entity.base.Supplier;
import com.glorious.model.entity.product.Variation;
import com.glorious.model.entity.restock.Invoice;
import com.glorious.model.entity.restock.Restock;
import com.glorious.model.form.restock.InvoiceForm;
import com.glorious.model.form.restock.invoice.InvoiceFormRestocks;
import com.glorious.model.form.restock.invoice.InvoiceFormRestocksVariations;
import com.glorious.model.mapper.base.StorehouseMapper;
import com.glorious.model.mapper.base.SupplierMapper;
import com.glorious.model.mapper.restock.InvoiceMapper;
import com.glorious.model.mapper.restock.RestockMapper;
import com.glorious.system.product.service.impl.ProductOfVariationAndStorehouseServiceImpl;
import com.glorious.system.product.service.implExtra.ProductServiceImplExtra;
import com.glorious.system.restock.service.InvoiceServiceExtra;
import com.glorious.utils.utils.basic.QTypeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class InvoiceServiceImplExtra implements InvoiceServiceExtra {

    @Autowired
    ProductServiceImplExtra serviceImplExtra;

    @Autowired
    StorehouseMapper storehouseMapper;

    @Autowired
    SupplierMapper supplierMapper;

    @Autowired
    RestockMapper restockMapper;

    @Autowired
    ProductOfVariationAndStorehouseServiceImpl productOfVariationAndStorehouseService;

    @Autowired
    BackendCacheService backendCacheService;

    final static Object lock = new Object();

    /**
    * 库存操作
    */
    @Transactional
    public Boolean insertInventory(
            List<InvoiceFormRestocks> formRestocks,
            InvoiceForm invoiceForm,
            Supplier supplier,
            Long invoiceID
            ) {

        // 锁住，只允许同一时间，只有一个加货程序运行
        synchronized (lock) {
            Long storehouseID = invoiceForm.getStorehouse();
            Long supplierID = invoiceForm.getSupplier();

            // 循环给产品加货
            for (InvoiceFormRestocks r: formRestocks) {

                Long productID = r.getProduct();

                // 1. 给 写的样式 加货
                List<InvoiceFormRestocksVariations> vs = r.getVariations();
                if (vs != null && !vs.isEmpty()) {

                    // 給指定的樣式入貨
                    for (InvoiceFormRestocksVariations v: vs) {

                        // 检测 入货 数量
                        if (QTypeUtil.serInt(v.getQuantity(), 0) == 0) throw new QLogicException("某項入貨產品中，入貨的數量數據異常");

                        // 执行 加货，可能存在 没有 productOfVariationAndStorehouse 数据的情况
                        Object result = productOfVariationAndStorehouseService.insertQuantity(
                                productID,
                                v.getVariation(),
                                storehouseID,
                                v.getQuantity());

                        // throw new QLogicException("自動出錯，測試回滾");
                        // 加货 若出异常
                        if (result instanceof String) throw new QLogicException(result + "，產品名稱：" + r.getName());
                    }
                }

                // 2. 给产品赋予 最新加货信息
                if (!serviceImplExtra.updRestockMsg(r, supplier)) throw new QLogicException("產品的最新入貨消息同步失敗，本次入貨已取消，請檢查入貨信息的完整性");

                // 3. 生成入货记录
                Restock restock = InvoiceFormRestocks.toRestock(r, invoiceID, supplierID, storehouseID);
                int i = restockMapper.insert(restock);
                if (i <= 0) throw new QLogicException("入貨紀錄新增失敗，本次入貨已取消，請稍微重識");

                // 4. 更新 入货平均价
                Boolean isOK = serviceImplExtra.updAverageRestockPrice(productID, r.getNet_price());
                // 不处理 isOK
            }
        }
        return true;
    }

    /**
    * 入货
    */
    @Override
    @Transactional
    public Object restock(InvoiceForm invoiceForm, Long invoiceID) {

        // 检测 仓库
        Long storehouseID = invoiceForm.getStorehouse();
        if (!QTypeUtil.isLong(storehouseID)) return "入貨倉庫 ID 異常";
        Storehouse storehouse = backendCacheService.getStorehouse(storehouseID); // storehouseMapper.selectById(storehouseID);
        if (storehouse == null) {
            return "未找到入貨倉庫，請檢查入貨倉庫是否存在";
        }

        // 检测 供应商
        Long supplierID = invoiceForm.getSupplier();
        if (!QTypeUtil.isLong(supplierID)) return "入貨供應商 ID 異常";
        Supplier supplier = backendCacheService.getSupplier(supplierID); // supplierMapper.selectById(invoiceForm.getSupplier());
        if (supplier == null) {
            return "未找到入貨供應商，請檢查供應商是否存在";
        }

        // 較嚴數據
        HashMap<Long, InvoiceFormRestocks> hm = new HashMap<>();
        // 去重复
        for (InvoiceFormRestocks r: invoiceForm.getRestocks()) { hm.put(r.getProduct(), r); }
        // 過濾無用數據
        List<InvoiceFormRestocks> formRestocks = hm.values().stream()
                .filter(r -> (r != null && r.getTotal_quantity() > 0))
                .collect(Collectors.toList());

        // 正式 执行入货
        return insertInventory(formRestocks, invoiceForm, supplier, invoiceID);
    }

}
