package com.glorious.model.vo.cashier;

import cn.hutool.json.JSONUtil;
import com.glorious.model.entity.product.Product;
import com.glorious.model.entity.product.remark.ProductRemark;
import com.glorious.model.vo.product.ProductDetailView;
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
public class CashierProductView {

    private Long id;
    private String product_id;
    private String name;
    private Date new_restock_date;

    private BigDecimal new_restock_price;
    private BigDecimal new_selling_price;
    private BigDecimal new_lowest_price;

    private List<InnerViewVariation> variations;
    // 库存详情
    private List<InnerViewStorehouse> storehouse_info;

    /**
     * 生成
     */
    public static CashierProductView byEntity(Product entity) {
        Optional<CashierProductView> ops = Optional.ofNullable(QBeanUtil.convert(entity, CashierProductView.class));
        ops.ifPresent(o -> {
            o.setId(entity.getId());
        });
        return ops.orElse(null);
    }
}
