package com.glorious.system.restock.controller;

import com.glorious.common.anno.Admin;
import com.glorious.common.define.webmvc.WebMvcRouter;
import com.glorious.common.mvc.AjaxResult;
import com.glorious.model.form.restock.TransferInventoryForm;
import com.glorious.system.restock.service.impl.InventoryServiceImpl;
import com.glorious.system.restock.webmvc.InventoryWebDefine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryController {

    @Autowired
    InventoryServiceImpl service;

    /**
    * 调货
    */
    @Admin
    @PatchMapping(InventoryWebDefine.TRANSFER + WebMvcRouter.ID)
    public AjaxResult transfer(@PathVariable Long id, @RequestBody TransferInventoryForm form) {
        return service.transferInventory(id, form);
    }
}
