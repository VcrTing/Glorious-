package com.glorious.model.define.converter.base;

import cn.hutool.json.JSONUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class BaseIdConverter extends BaseConverter {

    protected String json;
    protected List<Long> idsLong;
    protected List<String> idsString;

    /**
     * 去重复
     */
    public void distinct() {
        idsLong = distinct(idsLong);
        idsString = distinct(idsString);
    }

    /**
    * 新增
    */
    public void add(Long id) {
        idsLong.add(id);
        idsString.add(genStringId(id));
        distinct();
        json = JSONUtil.toJsonStr(idsString);
    }

    /**
     * 删除
     */
    public void remove(Long id) {
        idsLong = idsLong.stream().filter(i -> !Objects.equals(i, id)).collect(Collectors.toList());
        idsString = genStringIds(idsLong);
        distinct();
        json = JSONUtil.toJsonStr(idsString);
    }

    /**
    * 多重 存入 Long ID by String ID
    */
    public void addIdsString(List<String> srcIdsString) {
        idsString = srcIdsString.stream().distinct().collect(Collectors.toList());
        idsLong = idsString.stream().map(BaseConverter::genLongId).collect(Collectors.toList());
    }

    /**
     * 多重 存入 String ID by Long ID
     */
    public void addIdsLong(List<Long> srcIdsLong) {
        idsLong = srcIdsLong.stream().distinct().collect(Collectors.toList());
        idsString = srcIdsLong.stream().map(BaseConverter::genStringId).collect(Collectors.toList());
    }
}
