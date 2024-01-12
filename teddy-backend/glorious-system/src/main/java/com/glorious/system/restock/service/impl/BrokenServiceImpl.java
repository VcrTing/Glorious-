package com.glorious.system.restock.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.glorious.common.exception.QLogicException;
import com.glorious.common.mvc.AjaxResult;
import com.glorious.common.utils.PageHelperUtil;
import com.glorious.model.entity.restock.Broken;
import com.glorious.model.form.restock.BrokenForm;
import com.glorious.model.mapper.restock.BrokenMapper;
import com.glorious.model.mapperMulti.restock.BrokenDtoMapper;
import com.glorious.model.param.BrokenParam;
import com.glorious.model.vo.restock.BrokenView;
import com.glorious.system.TrashService;
import com.glorious.system.product.service.impl.ProductOfVariationAndStorehouseServiceImpl;
import com.glorious.system.restock.service.BrokenService;
import com.glorious.utils.utils.basic.QTypeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class BrokenServiceImpl extends ServiceImpl<BrokenMapper, Broken> implements BrokenService, TrashService {

    @Autowired
    ProductOfVariationAndStorehouseServiceImpl productOfVariationAndStorehouseService;

    @Autowired
    BrokenDtoMapper dtoMapper;

    @Override
    public <T> AjaxResult pag(T param) {
        BrokenParam brokenParam = (BrokenParam) param;
        PageHelperUtil.start(brokenParam);
        List<BrokenView> brokenViewList = BrokenView.byDTOs(dtoMapper.multiDeep(brokenParam.queryWrapper()));
        return PageHelperUtil.successResult(brokenViewList, brokenParam);
    }

    @Override
    public AjaxResult one(Long id) {
        return null;
    }

    /**
    * 新增 坏货
    */
    @Override
    @Transactional
    public <T> AjaxResult pos(T data) {
        BrokenForm form = (BrokenForm) data;
        Broken entity = BrokenForm.toEntity(form).orElse(null);
        if (entity == null) return AjaxResult.error("壞貨表單數據格式錯誤");
        // 产品库存 减货
        Object result = productOfVariationAndStorehouseService.removeQuantity(
                entity.getProduct_sql_id(),
                entity.getVariation_sql_id(),
                entity.getStorehouse_sql_id(),
                Integer.valueOf(form.getQuantity())
        );
        // 抛出错误，启动事务回滚
        if (result instanceof String) throw new QLogicException(result.toString());
        // 新增 坏货记录
        return AjaxResult.restfull(this.save(entity), entity);
    }

    @Override
    public <T> AjaxResult upd(Long id, T data) {
        return null;
    }

    /**
    * 取消坏货
    */
    @Override
    @Transactional
    public <T> AjaxResult del(Long id) {
        if (!QTypeUtil.isLong(id)) return AjaxResult.error("壞貨 ID 異常");
        Broken entity = this.getById(id);
        // 产品 库存 回归
        Object result = productOfVariationAndStorehouseService.insertQuantity(
                entity.getProduct_sql_id(),
                entity.getVariation_sql_id(),
                entity.getStorehouse_sql_id(),
                Integer.valueOf(entity.getQuantity())
        );
        // 抛出错误，启动事务回滚
        if (result instanceof String) throw new QLogicException(result.toString());
        // 删除 坏货记录
        return AjaxResult.restfull(this.removeById(id), entity);
    }
}
