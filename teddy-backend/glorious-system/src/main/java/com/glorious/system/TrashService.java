package com.glorious.system;

import com.glorious.common.mvc.AjaxResult;

public interface TrashService {
    /**
     * 删除
     */
    <T> AjaxResult del(Long id);
}
