package com.glorious.framework.component.tasks;

import com.glorious.framework.module.tasks.ProductHotSaleTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ProductHotSaleTask {

    @Autowired
    ProductHotSaleTaskService taskService;

    /**
    * 每日凌晨 5 點，查詢熱賣產品
    */
    @Scheduled(cron = "0 0 5 * * ?")
    public void hotSale() {
        try {
            taskService.writeExcel();
        } catch (Exception ignored) { }
    }

    // 方便測試，每兩分鐘執行一次
    @Scheduled(fixedDelay = 1000 * 60 * 2)
    public void hotSaleTest() {
        // System.out.println("執行 hotSaleTest");
        // taskService.writeExcel();
    }
}
