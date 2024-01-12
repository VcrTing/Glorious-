package com.glorious.model.form.order;

import cn.hutool.json.JSONUtil;
import com.glorious.model.entity.order.RefundedRecord;
import com.glorious.model.form.order.refund.InnerFormRefundInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefundForm {
    @NotNull(message = "退款仓库不为空")
    private Long storehouse;
    private String refunded_remarks;
    private List<InnerFormRefundInfo> refunded_info;

    public static RefundedRecord toEntity(RefundForm form, Long orderID) {

        RefundedRecord entity = new RefundedRecord();

        entity.setOrder_sql_id(orderID);
        entity.setStorehouse_sql_id(form.storehouse);
        entity.setRefunded_remarks(form.refunded_remarks);

        entity.setRefunded_info(JSONUtil.toJsonStr(form.getRefunded_info()));

        Integer num = 0;
        BigDecimal prs = new BigDecimal(0);
        for (InnerFormRefundInfo ri : form.getRefunded_info()) {
            num += ri.getRefunded_quantity();
            prs = prs.add(ri.getRefunded_price());
        }

        // 计算 总 价格，总 数量
        entity.setRefunded_quantity(num);
        entity.setRefunded_price(prs);

        return entity;
    }
}
