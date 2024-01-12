package com.glorious.framework.module.tasks;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.glorious.common.properties.GloriousSystemProperty;
import com.glorious.framework.component.StaticComponent;
import com.glorious.model.dto.excel.ProductInventoryExcel;
import com.glorious.model.dto.product.ProductOfVariationAndStorehouseDto;
import com.glorious.model.entity.order.Order;
import com.glorious.model.mapperMulti.product.ProductOfVariationAndStorehouseDtoMapper;
import com.glorious.utils.utils.file.QFileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Slf4j
@Component
public class ProductLessInventoryTaskService {

    @Autowired
    StaticComponent staticComponent;

    @Autowired
    GloriousSystemProperty systemProperty;

    @Autowired
    ProductOfVariationAndStorehouseDtoMapper dtoMapper;

    // 標題，9 個
    final static List<String> TITLES = Arrays.asList(
            "序號",
            "產品編號", "產品名稱", "樣式名稱",
            "剩餘庫存",
            "倉庫名稱", "倉庫負責人", "倉庫負責人電話", "倉庫地址"
    );
    // 文件名稱
    final static String NAME = "產品庫存缺貨表";
    final static String TYPE = "xls";

    /**
    * 生成 EXCEL
    */
    public void writeExcel() {

        // DATA LIST 轉換為 COLL LIST
        List<Map<String, Object>> rows = CollUtil.newArrayList(getRowList());
        // 定義文件儲存路徑
        String fileName = QFileUtil.genNameDay(NAME, TYPE);
        ExcelWriter writer = null;

        // 寫入
        try {
            // 生成路徑
            Path file = staticComponent.getPathLessInventory(fileName);
            // 清空文件夾
            QFileUtil.clearPath(staticComponent.getPathLessInventory());
            // 寫入
            writer = ExcelUtil.getWriter(QFileUtil.getPath(file));
            writer.write(rows, true);
            // 導出
            log.debug("少貨文件地址：" + QFileUtil.getPath(file));
        } catch (Exception ignored) {

        } finally {
            if (writer != null) writer.close();
        }
    }

    /**
     * 獲取 EXCEL 要用的 行列表
     */
    public ArrayList<Map<String, Object>> getRowList() {
        // 獲取 LIST
        List<ProductInventoryExcel> src = getExcelList();
        // 初始化 所有數據行
        ArrayList<Map<String, Object>> data = new ArrayList<>();
        // 初始化 序號
        int i = 0;
        // 制作 SHEET
        for (ProductInventoryExcel pie: src) {
            // 初始化
            i += 1;
            Map<String, Object> line = new LinkedHashMap<>();
            // 生成 行
            line.put(TITLES.get(0), i);
            line.put(TITLES.get(1), pie.getProduct().getProduct_id());
            line.put(TITLES.get(2), pie.getProduct().getName());
            line.put(TITLES.get(3), pie.getVariation().getName());
            line.put(TITLES.get(4), pie.getQuantity());
            line.put(TITLES.get(5), pie.getStorehouse().getName());
            line.put(TITLES.get(6), pie.getStorehouse().getContact_person());
            line.put(TITLES.get(7), pie.getStorehouse().getPhone_no());
            line.put(TITLES.get(8), pie.getStorehouse().getAddress());
            // 加入
            data.add(line);
        }
        // 返回
        return data;
    }

    /**
    * 獲取 EXCEL LIST
    */
    public List<ProductInventoryExcel> getExcelList() {
        List<ProductOfVariationAndStorehouseDto> dtoList = getOrderDtoByDate();
        return ProductInventoryExcel.byDtoList(dtoList);
    }

    /**
     * 獲取 庫存 小於 10 件的 產品
     */
    public List<ProductOfVariationAndStorehouseDto> getOrderDtoByDate() {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.lt("me.quantity", systemProperty.getLessInventoryLimit());
        // 估計 產品 ID 做排序整理
        queryWrapper.orderBy(true, true, "me.product_sql_id");
        return dtoMapper.excelDeep(queryWrapper);
    }
}
