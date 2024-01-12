package com.glorious.system.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.glorious.common.mvc.AjaxResult;
import com.glorious.common.utils.PageHelperUtil;
import com.glorious.framework.module.dataset.UserCacheService;
import com.glorious.model.entity.sys.User;
import com.glorious.model.form.sys.UserForm;
import com.glorious.model.mapper.sys.UserMapper;
import com.glorious.model.param.BaseParam;
import com.glorious.system.auth.service.UserService;
import com.glorious.system.auth.service.implExtra.UserServiceImplExtra;
import com.glorious.system.auth.service.implView.UserServiceImplView;
import com.glorious.utils.utils.basic.QTypeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserServiceImplView serviceImplView;

    @Autowired
    UserServiceImplExtra serviceImplExtra;

    @Autowired
    UserCacheService userCacheService;

    @Override
    public <T> AjaxResult pag(T param) {
        BaseParam baseParam = (BaseParam) param;

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderBy(baseParam.hasSort(), baseParam.isAsc(), "me.id");

        if (baseParam.hasSearch()) {
            queryWrapper.like("me.name", baseParam.getSearch()).or();
            queryWrapper.like("me.email", baseParam.getSearch()).or();
            queryWrapper.like("me.phone_no", baseParam.getSearch()).or();
        }

        PageHelperUtil.start(baseParam);
        return PageHelperUtil.successResult( serviceImplView.userViews(queryWrapper), baseParam );
    }

    @Override
    public AjaxResult one(Long id) {
        return QTypeUtil.isLong(id) ? AjaxResult.success(
                userCacheService.getUser(id)
        ) : AjaxResult.error("用戶 ID 異常");
    }

    @Override
    public <T> AjaxResult pos(T data) {
        User entity = UserForm.toEntity((UserForm) data).orElse(null);
        if (entity == null) return AjaxResult.error("用戶數據異常，新增失敗");
        if (serviceImplExtra.isSameUser(entity)) return AjaxResult.error("已存在相同的用戶");
        return AjaxResult.restfull(
                this.save(entity),
                userCacheService.setUser(entity)
        );
    }

    @Override
    public <T> AjaxResult upd(Long id, T data) {
        if (!QTypeUtil.isLong(id)) return AjaxResult.error("用戶 ID 異常");

        User entity = UserForm.toEntity((UserForm) data).orElse(null);
        if (entity == null) return AjaxResult.error("用戶數據異常。");

        entity.setId(id);
        User old = getById(id);

        // 电邮不同，则判断是否存在已有电邮
        if (!old.getEmail().equals(entity.getEmail())) {
            if (serviceImplExtra.isSameUser(entity)) return AjaxResult.error("已存在相同電郵地址的用戶了，用戶信息修改失敗");
        }
        return AjaxResult.restfull(
                this.updateById(entity),
                userCacheService.setUser(entity)
        );
    }
}
