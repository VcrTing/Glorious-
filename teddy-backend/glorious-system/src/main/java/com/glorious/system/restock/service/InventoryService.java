package com.glorious.system.restock.service;

import com.glorious.common.mvc.AjaxResult;
import com.glorious.model.form.restock.TransferInventoryForm;

public interface InventoryService {

    /**
    * 调货
    */
    AjaxResult transferInventory(Long pid, TransferInventoryForm form);
}
