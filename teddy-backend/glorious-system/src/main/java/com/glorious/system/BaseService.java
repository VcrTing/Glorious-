package com.glorious.system;

import com.glorious.common.mvc.AjaxResult;

public interface BaseService {

    /**
    * 分页查询
    */
    <T> AjaxResult pag(T param);

    /**
     * 单个
     */
    AjaxResult one(Long id);

    /**
    * 新增
    */
    <T> AjaxResult pos(T data);

    /**
     * 修改
     */
    <T> AjaxResult upd(Long id, T data);
}
