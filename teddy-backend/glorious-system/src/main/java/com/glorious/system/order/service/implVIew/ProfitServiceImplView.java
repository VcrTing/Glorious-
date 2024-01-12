package com.glorious.system.order.service.implVIew;

import com.glorious.common.mvc.AjaxResult;
import com.glorious.model.util.OrderProfitUtil;
import com.glorious.common.utils.PageHelperUtil;
import com.glorious.model.dto.order.OrderDto;
import com.glorious.model.mapperMulti.order.OrderDtoMapper;
import com.glorious.model.param.ProfitParam;
import com.glorious.model.vo.order.ProfitView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class ProfitServiceImplView {

    @Autowired
    OrderDtoMapper dtoMapper;

    /**
    * 深度 列表
    */
    public AjaxResult multiDeep(ProfitParam profitParam) {

        // 分页
        PageHelperUtil.start(profitParam);

        // 获取数据
        List<OrderDto> dtoList = dtoMapper.multiDeep(profitParam.queryWrapper());

        // 生成结果
        List<ProfitView> profitViewList = ProfitView.byDTOs(dtoList);
        AjaxResult result = PageHelperUtil.successResult( profitViewList, profitParam );

        // 计算 毛利率总和
        BigDecimal total = OrderProfitUtil.summationProfitAll(profitViewList);
        result.putExtra(total);

        // 返回
        return result;
    }
}
