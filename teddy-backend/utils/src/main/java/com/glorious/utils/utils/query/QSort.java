package com.glorious.utils.utils.query;

import java.util.HashMap;
import java.util.Map;


public class QSort {
    /*
    private String key;
    private String value;

    QSort(String key, String value) { this.key = key; this.value = value; }
    */

    final static String DEF = "id";
    final static String ASC = "ASC";
    final static String DESC = "DESC";

    final static String SORT_KEY = "sort";

    public static boolean isAsc(Map<String, Object> map) {
        if (map.get(SORT_KEY) == null) return false;
        return serValue(map.get(SORT_KEY)).equalsIgnoreCase(ASC);
    }

    /**
     * MAP 中 是否 含有 排序 字符
     * @params
     * @return
     */
    public static boolean hasSort(Map<String, Object> map) {
        if (map.get(SORT_KEY) == null) return false;
        return !map.get(SORT_KEY).toString().trim().isEmpty();
    }

    public static String serValue(Object src) {
        String v = DESC;
        if (src instanceof String) {
            String _sort = src.toString().trim();
            if (!_sort.isEmpty()) {
                String[] ks = _sort.split(":");
                if (ks.length == 2) { v = (ks[1].equalsIgnoreCase(ASC)) ? ASC : DESC; }
            }
        }
        return v;
    }
}
