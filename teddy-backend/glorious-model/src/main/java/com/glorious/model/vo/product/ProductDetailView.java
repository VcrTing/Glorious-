package com.glorious.model.vo.product;

import cn.hutool.json.JSONUtil;
import com.glorious.model.define.converter.LabelIdConverter;
import com.glorious.model.entity.product.Label;
import com.glorious.model.entity.product.Product;
import com.glorious.model.entity.product.remark.ProductRemark;
import com.glorious.model.vo.product.product.InnerViewBroken;
import com.glorious.model.vo.product.product.InnerViewRestock;
import com.glorious.model.vo.product.product.InnerViewStorehouse;
import com.glorious.model.vo.product.product.InnerViewVariation;
import com.glorious.utils.utils.bean.QBeanUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailView {

    private Long id;

    private String product_id;
    private Date create_date;
    private String name;

    private BigDecimal new_restock_price;
    private BigDecimal new_selling_price;
    private BigDecimal new_lowest_price;

    private String new_supplier;
    private Date new_restock_date;
    private BigDecimal average_restock_price;

    // 标签
    private List<LabelView> labels;
    private List<ProductRemark> remarks;
    private List<InnerViewVariation> variations;

    // 入貨紀錄
    private List<InnerViewRestock> restocks;

    // 坏货信息
    private List<InnerViewBroken> broken_products;

    // 库存详情
    private List<InnerViewStorehouse> storehouse_info;

    /**
    * 生成
    */
    public static ProductDetailView byEntity(Product entity) {
        Optional<ProductDetailView> ops = Optional.ofNullable(QBeanUtil.convert(entity, ProductDetailView.class));
        ops.ifPresent(o -> {
            o.setId(entity.getId());

            // 序列化 备注
            if (entity.getRemarks() != null) {
                o.setRemarks(JSONUtil.toList(entity.getRemarks(), ProductRemark.class));
            }
        });
        return ops.orElse(null);
    }
}
