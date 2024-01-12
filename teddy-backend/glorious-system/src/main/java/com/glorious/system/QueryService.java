package com.glorious.system;

import com.glorious.common.mvc.AjaxResult;

public interface QueryService {

    /**
     * 分页查询
     */
    <T> AjaxResult pag(T param);

    /**
     * 单个
     */
    AjaxResult one(Long id);
}
