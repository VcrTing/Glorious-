package com.glorious.system.auth.controller;

import com.glorious.framework.module.dataset.TestCacheService;
import com.glorious.framework.component.sender.BackendQueueSender;
import com.glorious.framework.component.sender.NotificationQueueSender;
import com.glorious.model.entity.restock.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysController {

    @Autowired
    TestCacheService testCacheService;

    @Autowired
    BackendQueueSender backendQueueSender;

    @Autowired
    NotificationQueueSender notificationQueueSender;

    @GetMapping("/test")
    public void test() {
        // 发送 TEST 消息
        // backendQueueSender.test();
        Invoice invoice = new Invoice();
        invoice.setInvoice_id("AAAXXXX");
        notificationQueueSender.invoiceInsertSuccess(invoice);
        /*
        int[] iss = { 1,2,3,4,5,6,7,8,9,10 };
        List<Thread> pool = new ArrayList<>();

        for (int i: iss) {
            pool.add(new Thread(() -> testCacheService.testSync(i)));
        }

        for (Thread t: pool) {
            t.start();
        }
         */

    }
}
