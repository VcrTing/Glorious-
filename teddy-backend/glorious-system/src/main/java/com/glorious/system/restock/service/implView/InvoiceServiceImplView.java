package com.glorious.system.restock.service.implView;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.glorious.model.dto.restock.InvoiceDto;
import com.glorious.model.entity.restock.Invoice;
import com.glorious.model.mapperMulti.restock.InvoiceDtoMapper;
import com.glorious.model.vo.restock.InvoiceDetailView;
import com.glorious.model.vo.restock.InvoiceSimplyView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class InvoiceServiceImplView {

    @Autowired
    InvoiceDtoMapper dtoMapper;

    /**
    *
    * @params
    * @return
    */
    public List<InvoiceSimplyView> simplyViews(QueryWrapper<Invoice> queryWrapper) {
        List<InvoiceDto> dtoList = dtoMapper.multiDeep(queryWrapper);
        return InvoiceSimplyView.byDtoList(dtoList);
    }

    /**
    *
    * @params
    * @return
    */
    public InvoiceDetailView detailView(Long invoiceID) {
        InvoiceDto dto = dtoMapper.oneDeep(invoiceID);
        log.debug("dto = {}", dto);
        return InvoiceDetailView.byDto(dto);
    }
}
