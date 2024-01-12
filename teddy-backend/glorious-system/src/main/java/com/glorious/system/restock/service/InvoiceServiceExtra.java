package com.glorious.system.restock.service;

import com.glorious.model.entity.base.Supplier;
import com.glorious.model.form.restock.InvoiceForm;
import com.glorious.model.form.restock.invoice.InvoiceFormRestocks;
import com.glorious.system.BaseService;

import java.util.List;

public interface InvoiceServiceExtra {

    /**
    * 依据 List<InvoiceFormRestocks> 正式入货
    */
    Boolean insertInventory(
            List<InvoiceFormRestocks> formRestocks,
            InvoiceForm invoiceForm,
            Supplier supplier,
            Long invoiceID
    );

    /**
    * 入货
    */
    Object restock(InvoiceForm invoiceForm, Long invoiceID);
}
