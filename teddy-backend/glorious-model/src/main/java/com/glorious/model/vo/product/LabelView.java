package com.glorious.model.vo.product;

import com.glorious.model.define.enums.EnumBoolean;
import com.glorious.model.entity.product.Label;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LabelView {
    private Long id;
    private String name;
    private Boolean isShow;

    public static LabelView byEntity(Label entity) {
        if (entity == null) return null;
        LabelView result = new LabelView();
        result.setId(entity.getId());
        result.setName(entity.getName());
        result.setIsShow(EnumBoolean.value(entity.getIs_show()));
        return result;
    }

    public static List<LabelView> byEntities(List<Label> entities) {
        return entities == null ? null :
            entities.stream()
                .map(LabelView::byEntity)
                .filter(l -> l.getId() != null)
                .collect(Collectors.toList());
    }
}
