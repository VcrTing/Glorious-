package com.glorious.model.param;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.glorious.model.define.converter.LabelIdConverter;
import com.glorious.model.entity.restock.Broken;
import com.glorious.utils.utils.basic.QTypeUtil;
import com.glorious.utils.utils.query.QDate;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.Date;

@Data
public class BrokenParam extends BaseParam {

    private String date;
    private Long product;
    private Long storehouse;

    public Boolean hasDate() { return StringUtils.hasText(date); }
    public Boolean hasProduct() { return QTypeUtil.isLong(product); }
    public Boolean hasStorehouse() { return QTypeUtil.isLong(storehouse); }


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
    public QueryWrapper<Broken> queryWrapper() {
        BrokenParam brokenParam = this;

        QueryWrapper<Broken> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("me.is_live", "1");
        queryWrapper.orderBy(brokenParam.hasSort(), brokenParam.isAsc(), "me.id");

        if (brokenParam.hasDate()) {
            queryWrapper.gt("me.date", QDate.serDate(brokenParam.getDate(), false));
        }
        if (brokenParam.hasProduct()) queryWrapper.eq("me.product_sql_id", brokenParam.getProduct());
        if (brokenParam.hasStorehouse()) queryWrapper.eq("me.storehouse_sql_id", brokenParam.getStorehouse());

        return queryWrapper;
    }
}
