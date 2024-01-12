package com.glorious.system.auth.service.implSecurity;

import com.glorious.common.exception.QAuthException;
import com.glorious.framework.model.auth.AuthUser;
import com.glorious.model.entity.sys.User;
import com.glorious.system.auth.service.impl.UserServiceImpl;
import com.glorious.system.auth.service.implExtra.UserServiceImplExtra;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthUserDetailService implements UserDetailsService {

    @Autowired
    UserServiceImplExtra userServiceImplExtra;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userServiceImplExtra.byEmail(username);
        if (user == null) throw new QAuthException("該用戶未註冊，用戶名：" + username);
        return AuthUser.init(user.getId(), user.getEmail(), user.getPassword(), user.getIs_admin());
    }
}
