package com.glorious.system.order.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.glorious.common.exception.QLogicException;
import com.glorious.common.mvc.AjaxResult;
import com.glorious.framework.module.dataset.BackendCacheService;
import com.glorious.model.define.enums.EnumOrderStatus;
import com.glorious.model.entity.base.Storehouse;
import com.glorious.model.entity.order.Order;
import com.glorious.model.entity.order.RefundedRecord;
import com.glorious.model.entity.order.product.OrderProduct;
import com.glorious.model.form.order.RefundForm;
import com.glorious.model.form.order.refund.InnerFormRefundInfo;
import com.glorious.model.mapper.base.StorehouseMapper;
import com.glorious.model.mapper.order.OrderMapper;
import com.glorious.model.mapper.order.RefundedRecordMapper;
import com.glorious.model.util.OrderRefundUtil;
import com.glorious.system.order.service.implExtra.OrderServiceImplExtra;
import com.glorious.system.product.service.impl.ProductOfVariationAndStorehouseServiceImpl;
import com.glorious.utils.utils.basic.QSumUtil;
import com.glorious.utils.utils.basic.QTypeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class RefundServiceImpl extends ServiceImpl<RefundedRecordMapper, RefundedRecord> {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    StorehouseMapper storehouseMapper;

    @Autowired
    OrderServiceImplExtra orderServiceImplExtra;

    @Autowired
    BackendCacheService backendCacheService;

    @Autowired
    ProductOfVariationAndStorehouseServiceImpl productOfVariationAndStorehouseService;

    final static Object lock = new Object();

    /**
    * 返还库存
    */
    public void refundInventory(List<InnerFormRefundInfo> refundInfoList, List<OrderProduct> orderProductList, Long storehouseID) {
        // 返还库存
        for (InnerFormRefundInfo ri: refundInfoList) {
            if (!QTypeUtil.isLongs(ri.getProduct(), ri.getVariation())) throw new QLogicException("退款消息列表內，產品 ID / 樣式 ID 異常");

            // 订单产品
            OrderProduct op = OrderRefundUtil.whichOrderProduct(orderProductList, ri);
            if (op == null) throw new QLogicException("該訂單內未有該產品的入貨消息");

            Boolean can = OrderRefundUtil.canRefund(op, ri);
            if (!can) throw new QLogicException("可退款的數量小於要退款的數量，退款失敗，本次退款已取消");

            // 同步 退款数量
            op.setRefunded_quantity(
                    QSumUtil.add(ri.getRefunded_quantity(), op.getRefunded_quantity())
            );

            // 执行加货
            Object res = productOfVariationAndStorehouseService.insertQuantity(
                    ri.getProduct(),
                    ri.getVariation(),
                    storehouseID,
                    ri.getRefunded_quantity()
            );

            // 加货不成功则抛异常
            if (res instanceof String) throw new QLogicException(res.toString());
        }
    }

    /**
    * 事务回滚
    */
    @Transactional
    public RefundedRecord refundTrans(Order order, RefundForm form, Long storehouseID) {

        // 购买的 产品
        List<OrderProduct> orderProductList = JSONUtil.toList(order.getOrdered_product(), OrderProduct.class);
        // 退款的 数据
        List<InnerFormRefundInfo> refundInfoList = OrderRefundUtil.distinctRefundInfo(form.getRefunded_info());

        // 返还 库存，該操作必須鎖住
        synchronized (lock) {
            refundInventory(refundInfoList, orderProductList, storehouseID);
        }

        // 修改订单状态，储存产品退款数量
        Object res = orderServiceImplExtra.refundAfter(order, orderProductList, form.getRefunded_remarks());
        if (res instanceof String) throw new QLogicException(res.toString());

        // 生成实体
        RefundedRecord record = RefundForm.toEntity(form, order.getId());

        // 储存 退款 表
        if (!this.save(record)) throw new QLogicException("因網絡波動，退款紀錄無法生成，本次退單已取消。");

        return record;
    }

    /**
    * 退款
    */
    @Transactional
    public AjaxResult refund(Long orderID, RefundForm form) {
        if (!QTypeUtil.isLong(orderID)) return AjaxResult.error("退款訂單 ID 異常");

        // 检测 仓库
        Long storehouseID = QTypeUtil.serLong(form.getStorehouse());
        if (storehouseID == null) return AjaxResult.error("退款倉庫 ID 異常");
        Storehouse storehouse = backendCacheService.getStorehouse(storehouseID); // storehouseMapper.selectById(storehouseID);
        if (storehouse == null) return AjaxResult.error("未找到要接收退單產品庫存的倉庫");

        // 检测 订单
        Order order = orderMapper.selectById(orderID);
        if (order == null) return AjaxResult.error("未找到要退款的訂單");
        if (order.getStatus().equals(EnumOrderStatus.canceled)) return AjaxResult.error("退款訂單的訂單狀態為已取消，本次退款失敗");

        // 执行 退单
        RefundedRecord record = refundTrans(order, form, storehouseID);

        // 返回
        return AjaxResult.restfull(record != null, record);
    }
}
