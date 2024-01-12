package com.glorious.model.form.order.checkout;

import com.glorious.model.entity.order.product.OrderProduct;
import com.glorious.model.entity.product.Product;
import com.glorious.model.util.OrderThingUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InnerFormCheckoutProduct {

    // 产品 ID
    private Long product;
    // 样式 ID
    private Long variation;
    // 购买 数量
    private Integer quantity;
    // 本產品 折扣
    private BigDecimal discount_deduction;

    // 退货 数量
    // private Integer refunded_quantity;

    // 需购买的产品
    private Product productEntity;

    public static OrderProduct toOrderProduct(InnerFormCheckoutProduct op, Product product) {

        if (op == null || product == null) return null;

        OrderProduct res = new OrderProduct();

        res.setProduct_sql_id(product.getId());
        res.setSelling_price(product.getNew_selling_price());
        res.setAverage_price(product.getNew_restock_price());

        int quantity = op.getQuantity() == null ? 0 : op.getQuantity();
        res.setQuantity(quantity);
        res.setVariation_sql_id(op.getVariation());
        res.setDiscount_deduction(op.getDiscount_deduction());

        // 总价
        res.setTotal_price(
                OrderThingUtil.computedProductTotalPrice(
                        product.getNew_selling_price(),
                        quantity,
                        op.getDiscount_deduction()));
        // 毛利率
        res.setTotal_profit(
                OrderThingUtil.computedProductTotalProfit(
                        product.getNew_selling_price(),
                        product.getNew_restock_price(),
                        quantity,
                        op.getDiscount_deduction()));

        return res;
    }
}
