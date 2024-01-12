package com.glorious.system.backend.service;

import com.glorious.common.mvc.AjaxResult;
import com.glorious.system.BaseService;

public interface SupplierService extends BaseService {

    AjaxResult one(Long id);
}
