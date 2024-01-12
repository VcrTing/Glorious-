package com.glorious.model.form.restock.invoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceFormRestocksVariations {

    private Long variation;
    private Integer quantity;
}
