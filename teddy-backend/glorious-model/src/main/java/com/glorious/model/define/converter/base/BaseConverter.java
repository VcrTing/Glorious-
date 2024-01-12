package com.glorious.model.define.converter.base;

import com.glorious.model.define.dataset.DatasetEntityConstants;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseConverter {

    /**
     * 去重复
     */
    public <T> List<T> distinct(List<T> src) {
        return new ArrayList<>(new HashSet<>(src));
    }

    /**
     * 生成 String ID
     */
    public static String genStringId(Long id) {
        return DatasetEntityConstants.JSON_ID_PREFIX + id + DatasetEntityConstants.JSON_ID_SUFFIX;
    }

    /**
    * 生成 Long ID
    */
    public static Long genLongId(String id) {
        return Long.parseLong( id
            .replace(DatasetEntityConstants.JSON_ID_PREFIX, "")
            .replace(DatasetEntityConstants.JSON_ID_SUFFIX, "")
            .trim()
        );
    }

    /**
    * 多重转换
    */
    public static List<String> genStringIds(List<Long> idsLong) {
        return idsLong.stream().map(BaseConverter::genStringId).collect(Collectors.toList());
    }
}
