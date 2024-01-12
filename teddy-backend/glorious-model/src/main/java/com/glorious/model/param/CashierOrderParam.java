package com.glorious.model.param;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.glorious.model.entity.order.Order;
import com.glorious.utils.utils.basic.QTypeUtil;
import com.glorious.utils.utils.query.QBetweenDate;
import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class CashierOrderParam extends BaseParam {

    private Integer time_period;
    private String order_id;
    private String status;
    private Long cashier;

    public Boolean hasTime_period() { return QTypeUtil.serInt(time_period) != null; }
    public Boolean hasOrder_id() { return StringUtils.hasText(order_id); }
    public Boolean hasStatus() { return StringUtils.hasText(status); }
    public Boolean hasCashier() {
        return QTypeUtil.isLong(cashier);
    }

    private String sort;
    private Short page;
    private Short size;
    private String search;

    public Boolean hasSort() {
        return sort != null && (!sort.trim().isEmpty());
    }
    public Boolean isAsc() {
        return hasSort() && sort.toUpperCase().contains("ASC");
    }
    public Boolean hasSearch() {
        return search != null && (!search.trim().isEmpty());
    }

    /**
    *
    * @params
    * @return
    */
    public QueryWrapper<Order> queryWrapper() {
        CashierOrderParam orderParam = this;

        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderBy(orderParam.hasSort(), orderParam.isAsc(), "me.id");

        if (orderParam.hasCashier())
            queryWrapper.eq("me.cashier_sql_id", this.getCashier()).or();

        if (orderParam.hasOrder_id())
            queryWrapper.like("me.order_id", orderParam.getOrder_id()).or();

        if (orderParam.hasStatus())
            queryWrapper.like("me.status", orderParam.getStatus()).or();

        // 时间
        if (orderParam.hasTime_period()) {
            boolean isExact = false;
            Integer day = - QTypeUtil.serInt(orderParam.getTime_period(), 0);
            QBetweenDate betweenDate = QBetweenDate.ofWhenDay(day, isExact);
            queryWrapper.lt("me.order_date", betweenDate.endDate(isExact));
            queryWrapper.gt("me.order_date", betweenDate.starDate(isExact));
        }

        return queryWrapper;
    }


}
