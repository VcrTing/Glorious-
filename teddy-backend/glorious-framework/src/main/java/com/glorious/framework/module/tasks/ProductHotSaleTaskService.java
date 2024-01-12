package com.glorious.framework.module.tasks;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.glorious.framework.component.StaticComponent;
import com.glorious.framework.module.dataset.ProductCacheService;
import com.glorious.model.dto.excel.OrderProductExcel;
import com.glorious.model.dto.order.OrderDto;
import com.glorious.model.entity.order.Order;
import com.glorious.model.entity.product.Product;
import com.glorious.model.mapper.product.ProductMapper;
import com.glorious.model.mapperMulti.order.OrderDtoMapper;
import com.glorious.utils.utils.basic.QSumUtil;
import com.glorious.utils.utils.file.QFileUtil;
import com.glorious.utils.utils.query.QBetweenDate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ProductHotSaleTaskService {

    @Autowired
    OrderDtoMapper orderDtoMapper;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    ProductCacheService productCacheService;

    @Autowired
    StaticComponent staticComponent;

    // 7 天之內 的 訂單
    final static short WHEN_DAY = 7;
    // 標題
    final static List<String> TITLES = Arrays.asList(
            "排名",
            "產品編號", "產品名稱", "樣式名稱",
            "銷量",
            "毛利率合計", "賣出價格合計", "退款數量合計"
    );
    // 文件名稱
    final static String NAME = "產品銷量排行表";
    final static String TYPE = "xls";

    /**
    * 計算 產品銷量
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
            Path file = staticComponent.getPathHotSale(fileName);
            // 清空文件夾
            QFileUtil.clearPath(staticComponent.getPathHotSale());
            // 寫入
            writer = ExcelUtil.getWriter(QFileUtil.getPath(file));
            writer.write(rows, true);
            // 導出
            log.debug("熱賣文件地址：" + QFileUtil.getPath(file));
        } catch (Exception ignored) {

        } finally {
            if (writer != null) writer.close();
        }
        // 返回
        // return fileName;
    }

    /**
    * 獲取 EXCEL 要用的 行列表
    */
    public ArrayList<Map<String, Object>> getRowList() {
        // 獲取 LIST
        List<OrderProductExcel> src = getOrderProductExcelList();
        // 初始化 所有數據行
        ArrayList<Map<String, Object>> data = new ArrayList<>();
        // 初始化 序號
        int i = 0;
        // 制作 SHEET
        for (OrderProductExcel ope: src) {
            // 初始化
            i += 1;
            Map<String, Object> line = new LinkedHashMap<>();
            // 生成 行
            line.put(TITLES.get(0), i);
            line.put(TITLES.get(1), ope.getProduct_id());
            line.put(TITLES.get(2), ope.getProduct_name());
            line.put(TITLES.get(3), ope.getVariation_name());
            line.put(TITLES.get(4), ope.getQuantity());
            line.put(TITLES.get(5), ope.getTotal_profit());
            line.put(TITLES.get(6), ope.getTotal_price());
            line.put(TITLES.get(7), ope.getRefunded_quantity());
            // 加入
            data.add(line);
        }
        // 返回
        return data;
    }

    /**
    * 獲取 OrderProductExcel LIST
    */
    public List<OrderProductExcel> getOrderProductExcelList() {
        // 獲取 全部 數據
        List<OrderProductExcel> src = OrderProductExcel.byEntities(getOrderDtoByDate());
        // 組合重複，計算數據
        HashMap<String, OrderProductExcel> hm = new HashMap<>();

        src.forEach(ope -> {
            OrderProductExcel inset = hm.get(ope.getUid());
            if (inset == null) {
                inset = ope;
            } else {
                // 組合 銷量
                inset.setQuantity(QSumUtil.add(inset.getQuantity(), ope.getQuantity()));
                // 組合 銷售額
                inset.setTotal_price(QSumUtil.add(inset.getTotal_price(), ope.getTotal_price()));
                // 組合 毛利率
                inset.setTotal_profit(QSumUtil.add(inset.getTotal_profit(), ope.getTotal_profit()));
                // 組合 退款數量
                inset.setRefunded_quantity(
                        QSumUtil.add(inset.getRefunded_quantity(), ope.getRefunded_quantity())
                );
            }
            hm.put(ope.getUid(), inset);
        });

        // 返回
        return hm.values().stream()
                // 組合 產品 信息
                .peek(ope -> {
                    Long pid = ope.getProduct_sql_id();
                    // 從緩存中拿 產品 數據
                    Product product = productCacheService.getProduct(pid);
                    if (product != null) {
                        ope.setProduct_id(product.getProduct_id());
                        ope.setProduct_name(product.getName());
                    }
                })
                // 銷量 排序
                .sorted((ope1, ope2) -> ope2.getQuantity() - ope1.getQuantity())
                .collect(Collectors.toList());
    }

    /**
    * 獲取日子之內的 訂單
    */
    public List<OrderDto> getOrderDtoByDate() {
        QBetweenDate betweenDate = QBetweenDate.ofWhenDay(- WHEN_DAY, false);
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.lt("me.order_date", betweenDate.getEndDate());
        queryWrapper.gt("me.order_date", betweenDate.getStarDate());
        return orderDtoMapper.multiDeep(queryWrapper);
    }
}
