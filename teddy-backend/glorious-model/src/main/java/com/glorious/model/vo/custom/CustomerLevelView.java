package com.glorious.model.vo.custom;

import com.glorious.model.entity.custom.CustomerLevel;
import com.glorious.utils.utils.basic.QTypeUtil;
import com.glorious.utils.utils.bean.QBeanUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerLevelView {

    private Long id;

    private String name;

    private BigDecimal discount;

    public static CustomerLevelView byEntity(CustomerLevel entity) {
        CustomerLevelView res = QBeanUtil.convert(entity, CustomerLevelView.class);
        if (res != null) {
            res.setId(entity.getId());
            res.setDiscount(QTypeUtil.serBigDecimal(entity.getDiscount(), null));
        }
        return res;
    }

    public static List<CustomerLevelView> byEntities(List<CustomerLevel> entities) {
        return (entities != null) ? entities.stream().map(CustomerLevelView::byEntity).collect(Collectors.toList()) : null;
    }
}
