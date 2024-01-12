package com.glorious.model.util;

import com.glorious.model.vo.order.ProfitView;
import com.glorious.model.vo.order.order.InnerViewPaymentMethod;
import com.glorious.utils.utils.basic.QSumUtil;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public abstract class OrderThingUtil {

    // 计算 订单 总金额
    public static BigDecimal computedTotal(List<InnerViewPaymentMethod> paymentMethods) {
        BigDecimal res = BigDecimal.ZERO;
        if (paymentMethods == null) return res;
        res.setScale(BigDecimal.ROUND_HALF_UP);
        for (InnerViewPaymentMethod method: paymentMethods) {
            if (method.getPrice() != null &&
                    method.getPrice().compareTo(BigDecimal.ZERO) > 0) {
                res = res.add(method.getPrice());
            }
        }
        return res;
    }

    // 生成 订单 ID
    public static String genOrderUUID() {
        int num = (int) (Math.random() * 100 + 1);
        String tim = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
        return "XD" + tim + num;
    }

    // 计算 OrderProduct 的 total_price
    public static BigDecimal computedProductTotalPrice(BigDecimal selling_price, Integer quantity, BigDecimal discount) {
        selling_price.setScale(BigDecimal.ROUND_HALF_UP);
        return selling_price.multiply(new BigDecimal(quantity)).subtract(discount);
    }
    // 计算 OrderProduct 的 profit
    public static BigDecimal computedProductTotalProfit(BigDecimal selling_price, BigDecimal average_price, Integer quantity, BigDecimal discount) {
        selling_price.setScale(BigDecimal.ROUND_HALF_UP);
        return (selling_price.subtract(average_price)).multiply(new BigDecimal(quantity)).subtract(discount);
    }

}
