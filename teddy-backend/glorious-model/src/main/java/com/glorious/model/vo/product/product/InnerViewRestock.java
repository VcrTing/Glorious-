package com.glorious.model.vo.product.product;

import cn.hutool.json.JSONUtil;
import com.glorious.model.dto.restock.RestockDto;
import com.glorious.model.entity.base.Supplier;
import com.glorious.model.entity.restock.Restock;
import com.glorious.model.vo.product.product.inner.InnerViewRestockDistribute;
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
public class InnerViewRestock {

    private Date restock_date;

    private BigDecimal restock_price;
    private BigDecimal lowest_price;
    private BigDecimal selling_price;

    private Integer quantity;

    private Supplier supplier;
    private String supplier_name;

    private List<InnerViewRestockDistribute> restock_distributes;

    public static InnerViewRestock byDTO(RestockDto dto) {
        Optional<InnerViewRestock> ops = Optional.ofNullable(QBeanUtil.convert(dto, InnerViewRestock.class));
        ops.ifPresent(r -> {
            r.setQuantity(dto.getTotal_quantity());
            if (dto.getSupplier() != null) {
                r.setSupplier_name(dto.getSupplier().getName());
            }
            r.setRestock_distributes(JSONUtil.toList(dto.getRestock_distribute(), InnerViewRestockDistribute.class));
        });
        return ops.orElse(null);
    }

    public static List<InnerViewRestock> byDTOs(List<RestockDto> dto) {
        return dto.stream()
                .filter(Objects::nonNull)
                .map(InnerViewRestock::byDTO).collect(Collectors.toList());
    }
}
