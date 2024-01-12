package com.glorious.system.order.service.implExtra;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.glorious.common.mvc.AjaxResult;
import com.glorious.model.define.enums.EnumOrderStatus;
import com.glorious.model.entity.order.Order;
import com.glorious.model.entity.order.product.OrderProduct;
import com.glorious.model.form.order.RefundForm;
import com.glorious.model.mapper.order.OrderMapper;
import com.glorious.model.util.OrderRefundUtil;
import com.glorious.model.vo.order.product.InnerViewOrderProduct;
import com.glorious.utils.utils.basic.QTypeUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class OrderServiceImplExtra extends ServiceImpl<OrderMapper, Order> {

    /**
    * 修改订单状态
    */
    @Transactional
    public AjaxResult updStatus(Long id, String statusStr) {

        if (!QTypeUtil.isLong(id)) return AjaxResult.error("訂單 ID 異常");
        if (!StringUtils.hasText(statusStr)) return AjaxResult.error("訂單狀態數據格式錯誤");

        Order order = this.getById(id);
        if (order.getStatus().getValue().equals(statusStr)) return AjaxResult.error("新舊訂單狀態相同，本次更改狀態已取消");

        EnumOrderStatus status = EnumOrderStatus.valueOf(statusStr);

        boolean isOK = this.lambdaUpdate()
                .eq(Order::getId, id)
                .set(Order::getStatus, status)
                .update();

        return AjaxResult.restfull(isOK, id);
    }

    /**
    * 订单退款相关
    */
    public Object refundAfter(Order order, List<OrderProduct> orderProductList, String remarks) {

        // 是否全部退款
        Boolean isFinish = OrderRefundUtil.isFinishRefund(orderProductList);
        // 更改订单状态
        EnumOrderStatus status = isFinish ? EnumOrderStatus.refunded : EnumOrderStatus.partially_refunded;

        boolean isOK = this.lambdaUpdate().
                eq(Order::getId, order.getId())
                .set(Order::getStatus, status)
                .set(Order::getRefunded_remarks, remarks)
                .update();

        return isOK ? true : "訂單狀態同步失敗，退款失敗，本次退款操作已取消";
    }
}
