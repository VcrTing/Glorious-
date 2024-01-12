package com.glorious.framework.module.dataset;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.glorious.common.define.datatset.RedisConstants;
import com.glorious.framework.component.tools.RedisTool;
import com.glorious.model.entity.base.Supplier;
import com.glorious.model.entity.base.Storehouse;
import com.glorious.model.entity.custom.CustomerLevel;
import com.glorious.model.mapper.base.StorehouseMapper;
import com.glorious.model.mapper.base.SupplierMapper;
import com.glorious.model.mapper.custom.CustomerLevelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@SuppressWarnings(value = {"unchecked", "rawtypes"})
@Component
public class BackendCacheService {

    @Autowired
    RedisTool redisTool;

    @Autowired
    SupplierMapper supplierMapper;

    @Autowired
    StorehouseMapper storehouseMapper;

    @Autowired
    CustomerLevelMapper customerLevelMapper;


    // 存活時間是 7 天
    final static int LIVE_MINUTE = 24 * 60 * 7;

    /**
    * 獲取 會員等級，沒有就從數據庫裡面取
    */
    public List<CustomerLevel> getCustomerLevelList() {
        List<CustomerLevel> res = redisTool.getCacheList(RedisConstants.KEY_CUSTOMER_LEVEL_LIST);
        return (res != null && !res.isEmpty()) ? res : refreshCustomerLevelList();
    }
    /**
     * ID 獲取
     */
    public CustomerLevel getCustomerLevel(Long id) {
        CustomerLevel res = redisTool.getObject(RedisConstants.KEY_CUSTOMER_LEVEL + id);
        return (res != null) ? res : customerLevelMapper.selectById(id);
    }
    /**
    * 刷新 會員等級
    */
    public List<CustomerLevel> refreshCustomerLevelList() {
        LambdaQueryWrapper<CustomerLevel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderBy(true, false, CustomerLevel::getId);
        List<CustomerLevel> res = customerLevelMapper.selectList(queryWrapper);
        setCustomerLevelList(res);
        return res;
    }
    /**
     * 设置 會員等級
     */
    public void setCustomerLevelList(List<CustomerLevel> src) {
        if (src != null && !src.isEmpty()) {
            redisTool.setCacheList(RedisConstants.KEY_CUSTOMER_LEVEL_LIST, src, redisTool.randomMinute(LIVE_MINUTE));
            for (CustomerLevel cl: src) {
                redisTool.setObject(RedisConstants.KEY_CUSTOMER_LEVEL + cl.getId(), cl, redisTool.randomMinute(LIVE_MINUTE));
            }
        }
    }


    /**
     * 獲取 倉庫，沒有就從數據庫裡面取
     */
    public List<Storehouse> getStorehouseList() {
        List<Storehouse> res = getStorehouseListCache();
        return (res != null && !res.isEmpty()) ? res : refreshStorehouseList();
    }
    public List<Storehouse> getStorehouseListCache() {
        return redisTool.getCacheList(RedisConstants.KEY_STOREHOUSE_LIST);
    }
    /**
    * ID 獲取
    */
    public Storehouse getStorehouse(Long id) {
        Storehouse res = redisTool.getObject(RedisConstants.KEY_STOREHOUSE + id);
        return res != null ? res : storehouseMapper.selectById(id);
    }
    /**
     * 刷新 倉庫
     */
    public List<Storehouse> refreshStorehouseList() {
        LambdaQueryWrapper<Storehouse> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderBy(true, false, Storehouse::getId);
        List<Storehouse> res = storehouseMapper.selectList(queryWrapper);
        setStorehouseList(res);
        return res;
    }
    /**
    * 设置 仓库
    */
    public void setStorehouseList(List<Storehouse> src) {
        if (src != null && !src.isEmpty()) {
            Integer minute = redisTool.randomMinute(LIVE_MINUTE);
            redisTool.setCacheList(RedisConstants.KEY_STOREHOUSE_LIST, src, minute);
            for (Storehouse s: src) {
                redisTool.setObject(RedisConstants.KEY_STOREHOUSE + s.getId(), s, minute);
            }
        }
    }

    /**
     * 獲取 供應商，沒有就從數據庫裡面取
     */
    public List<Supplier> getSupplierList() {
        List<Supplier> res = redisTool.getCacheList(RedisConstants.KEY_SUPPLIER_LIST);
        return (res != null && !res.isEmpty()) ? res : refreshSupplierList();
    }
    /**
     * ID 獲取
     */
    public Supplier getSupplier(Long id) {
        Supplier res = redisTool.getObject(RedisConstants.KEY_SUPPLIER + id);
        return res != null ? res : supplierMapper.selectById(id);
    }
    /**
     * 刷新 供應商
     */
    public List<Supplier> refreshSupplierList() {
        LambdaQueryWrapper<Supplier> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderBy(true, false, Supplier::getId);
        List<Supplier> res = supplierMapper.selectList(queryWrapper);
        setSupplierList(res);
        return res;
    }
    /**
     * 设置 供應商
     */
    public void setSupplierList(List<Supplier> src) {
        if (src != null && !src.isEmpty()) {
            redisTool.setCacheList(RedisConstants.KEY_SUPPLIER_LIST, src, redisTool.randomMinute(LIVE_MINUTE));
            for (Supplier s: src) {
                redisTool.setObject(RedisConstants.KEY_SUPPLIER + s.getId(), s, redisTool.randomMinute(LIVE_MINUTE));
            }
        }
    }
}
