package com.glorious.system.product.service;

import com.glorious.common.mvc.AjaxResult;

public interface ProductFileService {

    /**
    * 拿取 product hot sale excel
    */
    AjaxResult getHotSaleExcel();

    /**
    * 拿取 product inventory excel
    */
    AjaxResult getLessInventoryExcel();
}
