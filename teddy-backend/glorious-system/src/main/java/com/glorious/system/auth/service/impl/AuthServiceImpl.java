package com.glorious.system.auth.service.impl;

import com.glorious.common.mvc.AjaxResult;
import com.glorious.framework.model.auth.AuthUser;
import com.glorious.framework.module.auth.SysAdministratorService;
import com.glorious.framework.module.auth.SysLoginService;
import com.glorious.framework.component.tools.SecurityTool;
import com.glorious.framework.module.dataset.BackendCacheService;
import com.glorious.model.define.enums.EnumBoolean;
import com.glorious.model.entity.base.Storehouse;
import com.glorious.model.entity.sys.User;
import com.glorious.model.form.auth.SystemAuthLoginForm;
import com.glorious.model.form.auth.SystemAuthRegisterForm;
import com.glorious.model.mapper.base.StorehouseMapper;
import com.glorious.model.vo.auth.SystemAuthUserView;
import com.glorious.model.vo.sys.UserInfoView;
import com.glorious.system.auth.service.AuthService;
import com.glorious.system.auth.service.implExtra.UserServiceImplExtra;
import com.glorious.system.backend.service.implExtra.StorehouseServiceImplExtra;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    SecurityTool securityTool;

    @Autowired
    SysLoginService loginService;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    UserServiceImplExtra userServiceImplExtra;

    @Autowired
    StorehouseMapper storehouseMapper;

    @Autowired
    SysAdministratorService administratorService;

    @Autowired
    StorehouseServiceImplExtra storehouseServiceImplExtra;

    @Autowired
    BackendCacheService backendCacheService;

    /**
    * 測試
    */
    public void Test() {
        // List<Storehouse> storehouseList = backendCacheService.getStorehouseList(null);
        // System.out.println("緩存的 storehouseList = " + storehouseList);
    }

    /**
    * 执行 登录
    */
    @Override
    public AjaxResult login(SystemAuthLoginForm form) {
        Object result = loginService.login(form.getIdentifier(), form.getPassword());
        Test();
        if (result instanceof AuthUser) {
            AuthUser authUser = (AuthUser) result;
            return AjaxResult.success(
                    new SystemAuthUserView( authUser.getJwt(), authUser.getUserId(), authUser.getUsername() ));
        }
        return AjaxResult.error(result.toString());
    }

    /**
     * 执行 注册
     */
    @Override
    public AjaxResult creatAdministrator(SystemAuthRegisterForm form) {
        User old = userServiceImplExtra.byEmail(form.getIdentifier());
        if (old == null || old.getId() == null) {
            User user = administratorService.buildAdministrator(form.getIdentifier(), form.getPassword(), storehouseServiceImplExtra.last());
            return AjaxResult.restfull(userService.save(user), user);
        }
        return AjaxResult.error("已存在相同的用戶。");
    }

    /**
    * 查询 用户信息
    */
    @Override
    public AjaxResult userInfo() {
        AuthUser authUser = securityTool.getPrincipal();
        if (authUser == null) return AjaxResult.error("用戶登錄數據為空，請嘗試重新登錄。");
        // 查询 用户信息
        User user = this.userService.getById(authUser.getUserId());
        // 查询 仓库信息
        Storehouse storehouse = user.getStorehouse_sql_id() == null ? null : storehouseMapper.selectById(user.getStorehouse_sql_id());
        // 返回结果
        return AjaxResult.success(new UserInfoView(EnumBoolean.value(user.getIs_admin()), storehouse));
    }
}
