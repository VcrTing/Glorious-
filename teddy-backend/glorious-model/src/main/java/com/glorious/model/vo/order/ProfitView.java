package com.glorious.model.vo.order;

import com.glorious.model.define.enums.EnumOrderStatus;
import com.glorious.model.dto.order.OrderDto;
import com.glorious.model.entity.custom.Customer;
import com.glorious.model.entity.order.product.OrderProduct;
import com.glorious.model.entity.sys.User;
import com.glorious.utils.utils.bean.QBeanUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfitView {

    private Long id;
    private String order_id;
    private Date order_date;
    private EnumOrderStatus order_status;

    private Customer member;
    private User cashier;

    // 订单产品 的 总 金额
    private BigDecimal total_price;
    // 订单产品 的 总 毛利率 之和
    private BigDecimal total_profit;

    public static ProfitView byDTO(OrderDto dto) {
        Optional<ProfitView> ops = Optional.ofNullable(QBeanUtil.convert(dto, ProfitView.class));
        ops.ifPresent(o -> {
            o.setCashier(dto.getCashier());
            o.setMember(dto.getCustomer());
            o.setOrder_status(dto.getStatus());
            // 价格
            o.setTotal_price(dto.getTotal_price_after_refund());
            // 利率
            o.setTotal_profit(dto.getTotal_profit_after_refund());
        });
        return ops.orElse(null);
    }

    public static List<ProfitView> byDTOs(List<OrderDto> dtoList) {
        return dtoList.stream()
                .map(ProfitView::byDTO)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
