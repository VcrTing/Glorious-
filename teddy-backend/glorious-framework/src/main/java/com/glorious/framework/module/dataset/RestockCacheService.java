package com.glorious.framework.module.dataset;

import com.glorious.common.define.datatset.RedisConstants;
import com.glorious.framework.component.tools.RedisTool;
import com.glorious.model.entity.restock.Invoice;
import com.glorious.model.entity.sys.User;
import com.glorious.model.mapper.restock.InvoiceMapper;
import com.glorious.model.mapper.sys.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestockCacheService {

    @Autowired
    RedisTool redisTool;

    @Autowired
    InvoiceMapper invoiceMapper;

    // 存活時間是 1 天
    final static int LIVE_MINUTE = 24 * 60;

    /**
     * 获取 单个 發票
     */
    public Invoice getInvoice(Long id) {
        Invoice res = redisTool.getObject(RedisConstants.KEY_INVOICE + id);
        if (res == null) {
            res = invoiceMapper.selectById(id);
            setInvoice(res); }
        return res;
    }

    /**
     * 设置 单个 發票
     */
    public Invoice setInvoice(Invoice entity) {
        redisTool.setObject(RedisConstants.KEY_INVOICE + entity.getId(), entity, redisTool.randomMinute(LIVE_MINUTE));
        return entity;
    }

}
