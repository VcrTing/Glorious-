package com.glorious.system.product.controller;

import com.glorious.common.anno.Admin;
import com.glorious.common.mvc.AjaxResult;
import com.glorious.framework.component.StaticComponent;
import com.glorious.framework.module.tasks.ProductHotSaleTaskService;
import com.glorious.framework.module.tasks.ProductLessInventoryTaskService;
import com.glorious.system.product.webmvc.ProductFileWebDefine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductFileController {

    @Autowired
    StaticComponent staticComponent;

    @Autowired
    ProductHotSaleTaskService hotSaleTaskService;

    @Autowired
    ProductLessInventoryTaskService lessInventoryTaskService;

    /**
    * 熱賣產品 排行
    */
    @Admin
    @GetMapping(ProductFileWebDefine.EXPORT_HOT_SALE)
    public AjaxResult excelHotSale() {
        String name = staticComponent.getFileHotSale();
        if (name == null) {
            hotSaleTaskService.writeExcel();
            return AjaxResult.error("EXCEL 表格正在生成中，請稍候重識。");
        }
        return AjaxResult.success("", name);
    }

    /**
    * 少貨產品 列表
    */
    @Admin
    @GetMapping(ProductFileWebDefine.EXPORT_LESS_INVENTORY)
    public AjaxResult excelLessInventory() {
        String name = staticComponent.getFileLessInventory();
        if (name == null) {
            lessInventoryTaskService.writeExcel();
            return AjaxResult.error("EXCEL 表格正在生成中，請稍候重識。");
        }
        return AjaxResult.success("", name);
    }
}
