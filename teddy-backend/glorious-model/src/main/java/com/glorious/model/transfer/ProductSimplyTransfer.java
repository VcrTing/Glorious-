package com.glorious.model.transfer;

import com.glorious.model.define.converter.LabelIdConverter;
import com.glorious.model.dto.product.ProductOfVariationAndStorehouseDto;
import com.glorious.model.entity.product.Label;
import com.glorious.model.entity.product.Product;
import com.glorious.model.vo.product.LabelView;
import com.glorious.model.vo.product.ProductSimplyView;
import com.glorious.model.vo.product.product.InnerViewVariation;
import com.glorious.utils.utils.basic.QSumUtil;
import com.glorious.utils.utils.basic.QTypeUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Data
@AllArgsConstructor
public class ProductSimplyTransfer {

    private Integer total_stock;
    private List<LabelView> labels;
    private List<InnerViewVariation> variations;

    /**
    * 生成 LabelView
    */
    public static List<LabelView> toLabelViews(Product product, List<Label> labels) {
        List<Long> idsLong = LabelIdConverter.init(product).getIdsLong();
        if (idsLong != null) {
            // 组装 标签
            return labels.stream()
                    .filter(l -> idsLong.contains(l.getId()))
                    .map(LabelView::byEntity)
                    .collect(Collectors.toList());
        }
        return null;
    }

    /**
    * 生成 ProductSimplyView
    */
    public static ProductSimplyView genView(Product product, List<Label> labels, List<ProductOfVariationAndStorehouseDto> pvsDto) {

        // 生成 视图 对象
        ProductSimplyView one = ProductSimplyView.byEntity(product);

        // 生成
        ProductSimplyTransfer transfer = ProductSimplyTransfer.init(product, labels, pvsDto);

        // 设置值
        one.setLabels(transfer.getLabels());
        one.setVariations(transfer.getVariations());
        one.setTotal_stock(transfer.getTotal_stock());

        return one;
    }

    /**
     *
     */
    public static ProductSimplyTransfer init(Product product, List<Label> labels, List<ProductOfVariationAndStorehouseDto> pvsDto) {
        HashMap<Long, InnerViewVariation> hm = new HashMap<>();
        Integer total = 0;

        for (ProductOfVariationAndStorehouseDto pv: pvsDto) {
            // 主键
            Long key = pv.getVariation_sql_id();
            // 取出
            InnerViewVariation vv = hm.get(key);

            // 分支
            if (vv == null) {
                // 生成
                vv = InnerViewVariation.byInventory(pv.getVariation(), pv.getStorehouse(), pv.getQuantity());
            } else {
                // 加入 本库存
                vv.setQuantity( QSumUtil.add(vv.getQuantity(), pv.getQuantity()) );
            }
            // 存入
            hm.put(pv.getVariation_sql_id(), vv);

            // 加 总库存
            total = QSumUtil.add(total, pv.getQuantity());
        }

        return new ProductSimplyTransfer(
                total,
                toLabelViews(product, labels),
                hm.values().stream().filter(Objects::nonNull).collect(Collectors.toList())
        );
    }
}
