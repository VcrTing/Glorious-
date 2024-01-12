package com.glorious.model.define.converter;

import cn.hutool.json.JSONUtil;
import com.glorious.model.define.converter.base.BaseIdConverter;
import com.glorious.model.entity.product.Label;
import com.glorious.model.entity.product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LabelIdConverter extends BaseIdConverter {

    /**
     * 生成
     */
    public static LabelIdConverter init(Product product) {
        LabelIdConverter res = new LabelIdConverter();
        res.json = product.getLabels();
        res.addIdsString(JSONUtil.toList(res.json, String.class));
        return res;
    }

    /**
    * 生成
    */
    public static LabelIdConverter init(List<Long> ids) {
        LabelIdConverter res = new LabelIdConverter();
        res.addIdsLong(ids);
        res.json = JSONUtil.toJsonStr(res.idsString);
        return res;
    }

    /**
    * ids To Labels
    */
    public List<Label> idsToEntities(List<Label> src) {
        List<Label> res = new ArrayList<>();
        for (Long id: idsLong) {
            for (Label l: src) {
                if (Objects.equals(l.getId(), id)) res.add(l);
            }
        }
        return res;
    }
}
