package com.glorious.model.vo.order;

import com.glorious.model.define.enums.EnumOrderStatus;
import com.glorious.model.dto.order.OrderDto;
import com.glorious.model.entity.base.Storehouse;
import com.glorious.model.entity.custom.Customer;
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
public class OrderSimplyView {

    private Long id;
    private String order_id;
    private Date order_date;
    private EnumOrderStatus status;
    private BigDecimal total_price;

    private Customer member;
    private Storehouse order_shop;

    public static OrderSimplyView byDTO(OrderDto dto) {
        Optional<OrderSimplyView> ops = Optional.ofNullable(QBeanUtil.convert(dto, OrderSimplyView.class));
        ops.ifPresent(o -> {
            o.setId(dto.getId());
            o.setMember(dto.getCustomer());
            o.setOrder_shop(dto.getStorehouse());
        });
        return ops.orElse(null);
    }

    public static List<OrderSimplyView> byDTOs(List<OrderDto> dtoList) {
        return dtoList.stream().map(OrderSimplyView::byDTO).filter(Objects::nonNull).collect(Collectors.toList());
    }
}
