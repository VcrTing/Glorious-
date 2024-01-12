package com.glorious.model.util;

import com.glorious.model.entity.order.product.OrderProduct;
import com.glorious.model.vo.order.ProfitView;
import com.glorious.model.vo.order.order.InnerViewDiscount;
import com.glorious.utils.utils.basic.QSumUtil;

import java.math.BigDecimal;
import java.util.List;

public class OrderProfitUtil {

    // 计算订单产品毛利率之和
    public static BigDecimal reduceProductsProfit(List<OrderProduct> orderProductList) {
        BigDecimal res = BigDecimal.ZERO;
        if (orderProductList != null) {
            for (OrderProduct op: orderProductList) {
                res = QSumUtil.add(res, op.getTotal_profit());
            }
        }
        return res;
    }
    // 计算订单优惠之和
    public static BigDecimal reduceDiscounts(List<InnerViewDiscount> discountList) {
        BigDecimal res = BigDecimal.ZERO;
        if (discountList != null) {
            for (InnerViewDiscount d: discountList) {
                res = QSumUtil.add(res, d.getDiscount_deduction());
            }
        }
        return res;
    }

    // 计算订单总毛利率
    public static BigDecimal computedTotalProfit(
            List<OrderProduct> orderProductList,
            List<InnerViewDiscount> discountList) {
        return reduceProductsProfit(orderProductList).subtract(reduceDiscounts(discountList));
    }

    // 计算总毛利率 之和，使用的是 退款之后的 毛利率
    public static BigDecimal summationProfitAll(List<ProfitView> profitViews) {
        BigDecimal res = BigDecimal.ZERO;
        for (ProfitView pv: profitViews) {
            res = QSumUtil.add(pv.getTotal_profit(), res);
        }
        return res;
    }
}
