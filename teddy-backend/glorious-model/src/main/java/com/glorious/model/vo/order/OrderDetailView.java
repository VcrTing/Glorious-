package com.glorious.model.vo.order;

import cn.hutool.json.JSONUtil;
import com.glorious.model.define.enums.EnumOrderStatus;
import com.glorious.model.dto.order.OrderDto;
import com.glorious.model.entity.base.Storehouse;
import com.glorious.model.entity.custom.Customer;
import com.glorious.model.entity.custom.CustomerLevel;
import com.glorious.model.entity.sys.User;
import com.glorious.model.vo.order.order.InnerViewDiscount;
import com.glorious.model.vo.order.order.InnerViewPaymentMethod;
import com.glorious.model.vo.order.product.InnerViewOrderProduct;
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
public class OrderDetailView {

    private Long id;
    private String order_id;
    private Date order_date;
    private EnumOrderStatus status;
    private BigDecimal total_price;

    private User cashier;
    private Customer member;
    private Storehouse order_shop;
    private CustomerLevel member_level;

    private List<InnerViewDiscount> discount;
    private List<InnerViewPaymentMethod> payment_method;
    //
    private List<InnerViewOrderProduct> ordered_product;

    public static OrderDetailView byDTO(OrderDto dto) {
        Optional<OrderDetailView> ops = Optional.ofNullable(QBeanUtil.convert(dto, OrderDetailView.class));
        ops.ifPresent(o -> {
            o.setId(dto.getId());
            o.setMember(dto.getCustomer());
            o.setOrder_shop(dto.getStorehouse());
            o.setMember_level(dto.getCustomer_level());

            if (dto.getDiscount() != null) {
                o.setDiscount(JSONUtil.toList(dto.getDiscount(), InnerViewDiscount.class));
            }

            if (dto.getPayment_method() != null) {
                o.setPayment_method(JSONUtil.toList(dto.getPayment_method(), InnerViewPaymentMethod.class));
            }

            if (dto.getOrdered_product() != null) {
                o.setOrdered_product(JSONUtil.toList(dto.getOrdered_product(), InnerViewOrderProduct.class));
            }
        });
        return ops.orElse(null);
    }
}
