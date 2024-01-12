package com.glorious.system.restock.service.impl;

import com.glorious.common.exception.QLogicException;
import com.glorious.common.mvc.AjaxResult;
import com.glorious.model.entity.product.ProductOfVariationAndStorehouse;
import com.glorious.model.form.restock.TransferInventoryForm;
import com.glorious.system.product.service.impl.ProductOfVariationAndStorehouseServiceImpl;
import com.glorious.system.product.service.implExtra.ProductOfVariationAndStorehouseServiceImplExtra;
import com.glorious.system.restock.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    ProductOfVariationAndStorehouseServiceImpl productOfVariationAndStorehouseService;

    /**
    * 调货
    */
    @Override
    @Transactional
    public AjaxResult transferInventory(Long pid, TransferInventoryForm form) {

        // 检测
        ProductOfVariationAndStorehouse pvsFrom = productOfVariationAndStorehouseService.fetch(
            pid, form.getVariation(), form.getStorehouse_from()
        );
        if (pvsFrom == null) return AjaxResult.error("調貨的源頭倉庫未找到，調貨失敗");
        ProductOfVariationAndStorehouse pvsTo = productOfVariationAndStorehouseService.fetch(
                pid, form.getVariation(), form.getStorehouse_to()
        );
        if (pvsTo == null) return AjaxResult.error("調貨的目標倉庫未找到，調貨失敗");

        // From 仓库 减货
        Object resFrom = productOfVariationAndStorehouseService.removeQuantity(
                pvsFrom.getProduct_sql_id(),
                pvsFrom.getVariation_sql_id(),
                pvsFrom.getStorehouse_sql_id(),
                form.getQuantity()
        );
        if (resFrom instanceof String) throw new QLogicException(resFrom.toString());

        // To 仓库 加货
        Object resTo = productOfVariationAndStorehouseService.removeQuantity(
                pvsFrom.getProduct_sql_id(),
                pvsFrom.getVariation_sql_id(),
                pvsFrom.getStorehouse_sql_id(),
                form.getQuantity()
        );
        if (resTo instanceof String) throw new QLogicException(resTo.toString());

        // 返回
        return AjaxResult.success("產品加貨成功");
    }
}
