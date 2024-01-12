package com.glorious.model.transfer;

import com.glorious.model.define.converter.LabelIdConverter;
import com.glorious.model.dto.product.ProductOfVariationAndStorehouseDto;
import com.glorious.model.dto.restock.RestockDto;
import com.glorious.model.entity.base.Storehouse;
import com.glorious.model.entity.product.Label;
import com.glorious.model.entity.product.Product;
import com.glorious.model.entity.product.remark.ProductRemark;
import com.glorious.model.entity.restock.Broken;
import com.glorious.model.entity.restock.Restock;
import com.glorious.model.vo.product.LabelView;
import com.glorious.model.vo.product.ProductDetailView;
import com.glorious.model.vo.product.ProductSimplyView;
import com.glorious.model.vo.product.product.InnerViewBroken;
import com.glorious.model.vo.product.product.InnerViewRestock;
import com.glorious.model.vo.product.product.InnerViewStorehouse;
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
public class ProductDetailTransfer {
    /*
    // 标签
    private List<LabelView> labels;
    private List<InnerViewVariation> variations;
    // 入貨紀錄
    private List<InnerViewRestock> restocks;
    // 库存详情
    private List<InnerViewStorehouse> storehouse_info;
    */

    /**
    * 生成 详情
    */
    public static ProductDetailView genDetail(
            Product product,
            List<Label> labels,
            List<ProductOfVariationAndStorehouseDto> pvsDto,
            List<RestockDto> restocks,
            List<Broken> brokenList
            ) {

        ProductDetailView one = ProductDetailView.byEntity(product);

        if (one == null) return null;

        one.setRestocks( genRestocks(restocks) );
        // System.out.println("genRestocks(restocks) = " + genRestocks(restocks));
        one.setLabels( genLabels(product, labels) );
        // System.out.println("genLabels(product, labels) = " + genLabels(product, labels));
        one.setStorehouse_info( genStorehouses(pvsDto) );
        // System.out.println("genStorehouses(pvsDto) = " + genStorehouses(pvsDto));
        one.setVariations( genVariations(product, pvsDto) );
        // System.out.println("genVariations(product, pvsDto) = " + genVariations(product, pvsDto));

        one.setBroken_products( InnerViewBroken.byEntities(brokenList) );

        return one;
    }

    /**
    * 生成 入货
    */
    public static List<InnerViewRestock> genRestocks(List<RestockDto> rDto) {
        return InnerViewRestock.byDTOs(rDto);
    }
    /**
     * 生成 LabelView
     */
    public static List<LabelView> genLabels(Product product, List<Label> labels) {
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
    * 生成 样式
    */
    public static List<InnerViewVariation> genVariations(Product product, List<ProductOfVariationAndStorehouseDto> pvsDto) {
        HashMap<Long, ProductOfVariationAndStorehouseDto> hm = new HashMap<>();
        for (ProductOfVariationAndStorehouseDto pvs: pvsDto) { hm.put(pvs.getVariation_sql_id(), pvs); }
        return hm.values().stream()
                .map(pvs -> InnerViewVariation.byEntity(pvs.getVariation(), pvs.getStorehouse()))
                .collect(Collectors.toList());
    }
    /**
    * 生成 storehouse info
    */
    public static List<InnerViewStorehouse> genStorehouses(List<ProductOfVariationAndStorehouseDto> pvsDto) {
        List<InnerViewStorehouse> res = new ArrayList<>();
        HashMap<Long, ProductOfVariationAndStorehouseDto> hm = new HashMap<>();
        // 根据 storehouse 分类
        for (ProductOfVariationAndStorehouseDto pvs: pvsDto) {
            Long key = pvs.getStorehouse_sql_id();
            /*
            ProductOfVariationAndStorehouseDto exist = hm.get(key);
            if (exist != null) {
                pvs.setQuantity(QSumUtil.add(pvs.getQuantity(), exist.getQuantity()));
            }
            */
            hm.put(key, pvs);
        }
        // 生成
        for (ProductOfVariationAndStorehouseDto storehouseDto: hm.values()) {
            // 提取出 该 仓库所有样式
            List<InnerViewVariation> vvs = pvsDto.stream()
                    .filter(pvs -> storehouseDto.getStorehouse_sql_id().equals(pvs.getStorehouse_sql_id()))
                    .map(pvs -> InnerViewVariation.byInventory(pvs.getVariation(), pvs.getStorehouse(), pvs.getQuantity()))
                    .collect(Collectors.toList());
            // 生成结果
            InnerViewStorehouse vs = InnerViewStorehouse.byEntity(storehouseDto.getStorehouse(), vvs);
            res.add(vs);
        }
        return res;
    }
}
