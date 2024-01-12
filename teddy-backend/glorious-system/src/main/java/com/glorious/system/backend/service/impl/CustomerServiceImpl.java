package com.glorious.system.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.glorious.common.mvc.AjaxResult;
import com.glorious.common.utils.PageHelperUtil;
import com.glorious.model.entity.custom.Customer;
import com.glorious.model.form.custom.CustomerForm;
import com.glorious.model.mapper.custom.CustomerMapper;
import com.glorious.model.param.BaseParam;
import com.glorious.model.vo.custom.CustomerView;
import com.glorious.system.backend.service.CustomerService;
import com.glorious.system.backend.service.implView.CustomerServiceImplView;
import com.glorious.system.backend.webmvc.CustomerWebDefine;
import com.glorious.utils.utils.basic.QTypeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

    @Autowired
    CustomerMapper mapper;

    @Autowired
    CustomerServiceImplView serviceImplExtra;

    @Override
    public <T> AjaxResult pag(T param) {
        BaseParam baseParam = (BaseParam) param;

        LambdaQueryWrapper<Customer> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderBy(baseParam.hasSort(), baseParam.isAsc(), Customer::getId);

        if (baseParam.hasSearch()) {
            queryWrapper.like(Customer::getName, baseParam.getSearch()).or();
            queryWrapper.like(Customer::getEmail, baseParam.getSearch()).or();
            queryWrapper.like(Customer::getPhone_no, baseParam.getSearch()).or();
            queryWrapper.like(Customer::getMember_id, baseParam.getSearch()).or();
        }

        PageHelperUtil.start(baseParam);
        return PageHelperUtil.successResult( serviceImplExtra.entitiesToVos(this.list(queryWrapper)), baseParam );
    }

    @Override
    public AjaxResult one(Long id) {
        CustomerView res = serviceImplExtra.entityToVo(this.getById(id));
        return AjaxResult.restfull((res != null), res);
    }

    @Override
    public <T> AjaxResult pos(T data) {
        Optional<Customer> ops = CustomerForm.toEntity((CustomerForm) data);

        // 自增 ID
        ops.ifPresent( entity -> {
            long resID = CustomerWebDefine.INIT_ID;
            // 查询最后一个用户
            Customer last = mapper.last();
            // 自增 ID
            if (last != null) {
                Long lastID = QTypeUtil.serLong(last.getMember_id());
                if (lastID != null) resID = lastID + 1L;
            }
            entity.setMember_id(Long.toString(resID));
        });

        Customer entity = ops.orElse(null);
        if (entity == null) return AjaxResult.error("新增客人的表單數據格式錯誤");
        return AjaxResult.restfull(this.save(entity), serviceImplExtra.entityToVo(entity));
    }

    @Override
    public <T> AjaxResult upd(Long id, T data) {
        if (!QTypeUtil.isLong(id)) return AjaxResult.error("客人 ID 異常");

        Customer entity = CustomerForm.toEntity((CustomerForm) data).orElse(null);
        log.debug("修改的 客人数据 = " + entity);
        if (entity == null) return AjaxResult.error("修改客人的表單數據格式錯誤");
        entity.setId(id);
        return AjaxResult.restfull(updateById(entity), serviceImplExtra.entityToVo(entity));
    }
}
