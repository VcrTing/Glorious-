package com.glorious.model.vo.restock;

import com.glorious.model.dto.restock.BrokenDto;
import com.glorious.model.entity.base.Storehouse;
import com.glorious.model.entity.product.Product;
import com.glorious.utils.utils.bean.QBeanUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrokenView {

    private Long id;

    private Date date;
    private String remarks;
    private Short quantity;

    private String product_id;
    private String product_name;
    private String storehouse_name;

    private Product product;
    private Storehouse storehouse;

    public static BrokenView byDTO(BrokenDto dto) {
        return QBeanUtil.convert(dto, BrokenView.class);
    }

    public static List<BrokenView> byDTOs(List<BrokenDto> dtoList) {
        return dtoList.stream().map(BrokenView::byDTO).filter(Objects::nonNull).collect(Collectors.toList());
    }
}
