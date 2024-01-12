package com.glorious.framework.component.tasks;

import com.glorious.framework.module.tasks.ProductLessInventoryTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ProductLessInventoryTask {

    @Autowired
    ProductLessInventoryTaskService taskService;

    /**
     * 每日凌晨 4 點，查詢 少貨的產品
     */
    @Scheduled(cron = "0 0 4 * * ?")
    public void hotSale() {
        try {
            taskService.writeExcel();
        } catch (Exception ignored) { }
    }

    // 方便測試，每三分鐘執行一次
    @Scheduled(fixedDelay = 1000 * 60 * 2)
    public void hotSaleTest() {
        // System.out.println("hotSaleTest");
        // taskService.writeExcel();
    }
}
