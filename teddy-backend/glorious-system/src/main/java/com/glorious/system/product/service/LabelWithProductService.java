package com.glorious.system.product.service;

import com.glorious.common.mvc.AjaxResult;

public interface LabelWithProductService {

    /**
    * 新增标签
    */
    AjaxResult addLabel(Long pid, Long lid);

    /**
    * 删除标签
    */
    AjaxResult delLabel(Long pid, Long lid);
}
