package com.glorious.model.param;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.glorious.model.define.converter.LabelIdConverter;
import com.glorious.model.entity.product.Product;
import com.glorious.model.param.BaseParam;
import com.glorious.utils.utils.basic.QTypeUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@Data
public class ProductParam extends BaseParam {

    private Long label;
    private Long supplier;
    private String new_restock_date;

    public Boolean hasLabel() { return QTypeUtil.isLong(label); }
    public String getLabelID() { return LabelIdConverter.genStringId(label); }
    public Boolean hasSupplier() { return QTypeUtil.isLong(supplier); }
    public Boolean hasNew_restock_date() { return StringUtils.hasText(new_restock_date); }


    private String sort;
    private Short page;
    private Short size;
    private String search;

    public Boolean hasSort() {
        return sort != null && (!sort.trim().isEmpty());
    }
    public Boolean isAsc() {
        return hasSort() && sort.toUpperCase().contains("ASC");
    }
    public Boolean hasSearch() {
        return search != null && (!search.trim().isEmpty());
    }

    /**
    *
    * @params
    * @return
    */
    public LambdaQueryWrapper<Product> genQueryWrapper() {
        LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderBy(this.hasSort(), this.isAsc(), Product::getId);

        if (this.hasSearch()) {
            queryWrapper.like(Product::getName, this.getSearch()).or();
            queryWrapper.like(Product::getProduct_id, this.getSearch()).or();
        }
        if (this.hasLabel())
            queryWrapper.like(Product::getLabels, this.getLabelID()).or();
        if (this.hasSupplier())
            queryWrapper.like(Product::getNew_supplier_sql_id, this.getSupplier()).or();
        if (this.hasNew_restock_date())
            queryWrapper.like(Product::getNew_restock_date, this.getNew_restock_date()).or();

        return queryWrapper;
    }
}
