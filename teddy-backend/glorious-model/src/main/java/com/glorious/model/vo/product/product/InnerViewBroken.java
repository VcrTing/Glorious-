package com.glorious.model.vo.product.product;

import com.glorious.model.entity.base.Storehouse;
import com.glorious.model.entity.restock.Broken;
import com.glorious.utils.utils.bean.QBeanUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InnerViewBroken {

    private Long id;
    private Integer quantity;
    private Date date;
    private String remarks;

    private Object variation;
    private Storehouse storehouse;

    public static InnerViewBroken byEntity(Broken entity) {
        Optional<InnerViewBroken> ops = Optional.ofNullable(QBeanUtil.convert(entity, InnerViewBroken.class));
        ops.ifPresent(o -> {
            o.setId(entity.getId());
        });
        return ops.orElse(null);
    }

    public static List<InnerViewBroken> byEntities(List<Broken> brokenList) {
        return brokenList == null ? null : brokenList.stream().map(InnerViewBroken::byEntity).filter(Objects::nonNull).collect(Collectors.toList());
    }
}
