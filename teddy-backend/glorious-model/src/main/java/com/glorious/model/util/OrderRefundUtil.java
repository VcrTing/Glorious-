package com.glorious.model.util;

import com.glorious.model.entity.order.product.OrderProduct;
import com.glorious.model.form.order.refund.InnerFormRefundInfo;
import com.glorious.utils.utils.basic.QSumUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class OrderRefundUtil {

    // 整合 一下 InnerFormRefundInfo，再加上去重复
    public static List<InnerFormRefundInfo> distinctRefundInfo(List<InnerFormRefundInfo> ris) {
        if (ris == null) return new ArrayList<>();
        return ris.stream().distinct().filter(Objects::nonNull).collect(Collectors.toList());
    }

    // 订单是否 全部 退款完成
    public static Boolean isFinishRefund(List<OrderProduct> opList) {
        for (OrderProduct op: opList) {
            if (!QSumUtil.biggerInt(op.getRefunded_quantity(), op.getQuantity())) {
                return false;
            }
        }
        return true;
    }

    // 定位订单产品
    public static OrderProduct whichOrderProduct(List<OrderProduct> opList, InnerFormRefundInfo ri) {
        // 过滤
        OrderProduct one = null;
        for(OrderProduct op: opList) {
            if (
                op.getProduct_sql_id().equals(ri.getProduct()) &&
                op.getVariation_sql_id().equals(ri.getVariation())
            ) {
                one = op;
            }
        }
        // 返回
        return one;
    }

    // 该产品是否可退款
    public static Boolean canRefund(OrderProduct one, InnerFormRefundInfo ri) {
        // 能退款数量
        Integer canRefundQuantity = QSumUtil.sub(one.getQuantity(), one.getRefunded_quantity());
        // 能退款数量 >= 退款数量
        return QSumUtil.biggerInt(canRefundQuantity, ri.getRefunded_quantity());
    }
}
