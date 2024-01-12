package com.glorious.model.param;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.glorious.model.entity.order.Order;
import com.glorious.utils.utils.basic.QTypeUtil;
import com.glorious.utils.utils.query.QBetweenDate;
import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class ProfitParam extends BaseParam {

    private String order_id;
    private String status;
    private Long cashier;
    private String startDate;
    private String endDate;
    private Integer time_period;

    public Boolean hasOrder_id() { return StringUtils.hasText(order_id); }
    public Boolean hasStatus() { return StringUtils.hasText(status); }
    public Boolean hasCashier() { return QTypeUtil.isLong(cashier); }
    public Boolean hasTime_period() { return QTypeUtil.serInt(time_period) != null; }
    public Boolean hasDate() {
        return StringUtils.hasText(this.startDate) && StringUtils.hasText(this.endDate );
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

        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        ProfitParam orderParam = this;

        queryWrapper.orderBy(orderParam.hasSort(), orderParam.isAsc(), "me.id");

        if (orderParam.hasSearch())
            queryWrapper.like("me.order_id", orderParam.getSearch()).or();

        if (orderParam.hasOrder_id())
            queryWrapper.like("me.order_id", orderParam.getOrder_id()).or();

        if (orderParam.hasStatus())
            queryWrapper.eq("me.order_status", orderParam.getStatus()).or();

        if (orderParam.hasCashier())
            queryWrapper.eq("me.cashier_sql_id", orderParam.hasCashier().toString()).or();

        // 时间
        boolean isExact = false;
        QBetweenDate betweenDate = null;

        if (orderParam.hasTime_period()) {
            Integer day = - QTypeUtil.serInt(orderParam.getTime_period(), 0);
            betweenDate = QBetweenDate.ofWhenDay(day, isExact);
        } else {
            if (orderParam.hasDate()) {
                betweenDate = QBetweenDate.init(orderParam.getStartDate(), orderParam.getEndDate(), isExact);
            }
        }

        if (betweenDate != null) {
            queryWrapper.gt("me.order_date", betweenDate.endDate(isExact));
            queryWrapper.lt("me.order_date", betweenDate.starDate(isExact));
        }

        return queryWrapper;
    }
}
