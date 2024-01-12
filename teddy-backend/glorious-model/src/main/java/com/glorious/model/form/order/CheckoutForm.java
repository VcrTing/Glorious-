package com.glorious.model.form.order;

import cn.hutool.json.JSONUtil;
import com.glorious.model.define.enums.EnumOrderStatus;
import com.glorious.model.entity.custom.Customer;
import com.glorious.model.entity.custom.CustomerLevel;
import com.glorious.model.entity.order.Order;
import com.glorious.model.entity.order.product.OrderProduct;
import com.glorious.model.entity.sys.User;
import com.glorious.model.form.order.checkout.InnerFormCheckoutProduct;
import com.glorious.model.util.OrderProfitUtil;
import com.glorious.model.util.OrderThingUtil;
import com.glorious.model.vo.order.order.InnerViewDiscount;
import com.glorious.model.vo.order.order.InnerViewPaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.annotation.OrderUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutForm {

    private Long member;
    private EnumOrderStatus status;

    private List<InnerViewDiscount> discount;
    private List<InnerViewPaymentMethod> payment_method;
    private List<InnerFormCheckoutProduct> ordered_product;

    public static Order toEntity(CheckoutForm form, User cashier, Customer customer) {
        Order entity = new Order();

        // 基础信息
        entity.setOrder_id(OrderThingUtil.genOrderUUID());
        entity.setOrder_date(new Date());
        entity.setStatus(form.getStatus());

        // 联表
        entity.setCashier_sql_id(cashier.getId());
        entity.setStorehouse_sql_id(cashier.getStorehouse_sql_id());

        // 订单 可以没 用户
        if (customer != null) {
            entity.setCustomer_sql_id(customer.getId());
            entity.setCustomer_level_sql_id(customer.getCustomer_level_sql_id());
        }

        // 折扣 和 付款
        entity.setDiscount(JSONUtil.toJsonStr(form.discount));
        entity.setPayment_method(JSONUtil.toJsonStr(form.payment_method));

        // 购买的产品
        List<OrderProduct> orderProductList = asyncOrderProduct(form.ordered_product);
        entity.setOrdered_product(JSONUtil.toJsonStr(orderProductList));

        // 价格
        entity.setTotal_price(OrderThingUtil.computedTotal(form.getPayment_method()));
        entity.setTotal_price_after_refund( entity.getTotal_price() );
        // 毛利率
        entity.setTotal_profit(OrderProfitUtil.computedTotalProfit(orderProductList, form.discount));
        entity.setTotal_profit_after_refund( entity.getTotal_profit() );


        // 返回
        return entity;
    }

    // 生成 OrderProduct
    public static List<OrderProduct> asyncOrderProduct(List<InnerFormCheckoutProduct> checkoutProducts) {
        return checkoutProducts.stream()
                .map(cp -> InnerFormCheckoutProduct.toOrderProduct(cp, cp.getProductEntity()))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
