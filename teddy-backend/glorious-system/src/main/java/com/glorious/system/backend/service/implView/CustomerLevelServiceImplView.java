package com.glorious.system.backend.service.implView;

import com.glorious.framework.module.dataset.BackendCacheService;
import com.glorious.model.entity.custom.CustomerLevel;
import com.glorious.model.vo.custom.CustomerLevelView;
import com.glorious.system.backend.service.impl.CustomerLevelServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class CustomerLevelServiceImplView {

    @Autowired
    CustomerLevelServiceImpl service;

    @Autowired
    BackendCacheService backendCacheService;

    /**
     * 获取 CustomerLevelView LIST
     */
    public List<CustomerLevelView> customerLevelViewList() {

        // 从 缓存 里面拿 LEVEL LIST
        List<CustomerLevel> customerLevelList = backendCacheService.getCustomerLevelList();

        // 打印
        // log.debug("缓存中获取的 customerLevelList = " + customerLevelList);

        // 返回
        return CustomerLevelView.byEntities(customerLevelList);
    }
}
