package com.glorious.system.restock.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.glorious.common.exception.QLogicException;
import com.glorious.common.mvc.AjaxResult;
import com.glorious.common.utils.PageHelperUtil;
import com.glorious.framework.component.sender.NotificationQueueSender;
import com.glorious.model.entity.restock.Invoice;
import com.glorious.model.form.restock.InvoiceForm;
import com.glorious.model.mapper.restock.InvoiceMapper;
import com.glorious.model.param.InvoiceParam;
import com.glorious.model.vo.restock.InvoiceSimplyView;
import com.glorious.system.restock.service.InvoiceService;
import com.glorious.system.restock.service.implExtra.InvoiceServiceImplExtra;
import com.glorious.system.restock.service.implView.InvoiceServiceImplView;
import com.glorious.utils.utils.basic.QTypeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class InvoiceServiceImpl extends ServiceImpl<InvoiceMapper, Invoice> implements InvoiceService {

    @Autowired
    InvoiceServiceImplExtra serviceImplExtra;

    @Autowired
    InvoiceServiceImplView serviceImplView;

    @Resource
    NotificationQueueSender notificationQueueSender;

    /**
    * 深度 列表
    */
    @Override
    public <T> AjaxResult pag(T param) {
        InvoiceParam invoiceParam = (InvoiceParam) param;
        PageHelperUtil.start(invoiceParam);
        List<InvoiceSimplyView> simplyViewList = serviceImplView.simplyViews(invoiceParam.queryWrapper());
        log.debug("simplyViewList = " + simplyViewList);
        return PageHelperUtil.successResult(simplyViewList, invoiceParam );
    }

    /**
    * 查询 一个
    */
    @Override
    public AjaxResult one(Long id) {
        if (!QTypeUtil.isLong(id)) return AjaxResult.error("发票 ID 异常");
        return AjaxResult.success(serviceImplView.detailView(id));
    }

    /**
    * 新增 入货
    */
    @Override
    @Transactional
    public <T> AjaxResult pos(T data) {
        InvoiceForm invoiceForm = (InvoiceForm) data;

        // 生成 实体
        Invoice entity = InvoiceForm.toEntity(invoiceForm).orElse(null);
        if (entity == null) return AjaxResult.error("發票數據格式錯誤");

        // 储存 实体
        boolean isOK = this.save(entity);
        if (!isOK) throw new QLogicException("因網絡波動，發票入貨消息儲存失敗，本次入貨已取消");

        // 开展 入货
        Object result = serviceImplExtra.restock(invoiceForm, entity.getId());
        if (result instanceof String) throw new QLogicException(result.toString());

        log.debug("Invoice = " + entity);

        // 入货成功
        notificationQueueSender.invoiceInsertSuccess(entity); // notificationService.notifyInvoiceInsert(entity);

        // 返回 结果
        return AjaxResult.success(entity);
    }

    @Override
    public <T> AjaxResult upd(Long id, T data) {
        return null;
    }
}
