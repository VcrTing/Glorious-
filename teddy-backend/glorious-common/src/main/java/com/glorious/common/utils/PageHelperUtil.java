package com.glorious.common.utils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.glorious.common.mvc.AjaxResult;
import com.glorious.model.param.BaseParam;

import java.util.List;

public abstract class PageHelperUtil {

    final static short PAGE = 1;
    final static short SIZE = 25;
    final static short MIN_SIZE = 10;

    public static <T> boolean isNotInMinSize(List<T> list) {
        return list != null && list.size() >= MIN_SIZE;
    }

    public static Short mustPage(BaseParam param) {
        Short res = PAGE;
        if (param != null) res = param.getPage();
        return res == null ? PAGE : res;
    }
    public static Short mustSize(BaseParam param) {
        Short res = SIZE;
        if (param != null) res = param.getSize();
        return res == null ? SIZE : res;
    }

    public static void start(BaseParam param) {
        PageHelper.startPage(mustPage(param), mustSize(param));
    }

    public static <T> AjaxResult successResult(List<T> lists, Short size) {
        PageInfo<T> pageInfo = new PageInfo<>(lists);
        pageInfo.setPageSize(size == null ? SIZE : size);
        return AjaxResult.success(pageInfo);
    }
    public static <T> AjaxResult successResult(List<T> lists, BaseParam param) {
        return successResult(lists, mustSize(param));
    }
}
