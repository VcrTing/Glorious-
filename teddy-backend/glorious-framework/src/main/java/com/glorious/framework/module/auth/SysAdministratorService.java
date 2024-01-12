package com.glorious.framework.module.auth;

import com.glorious.model.define.enums.EnumBoolean;
import com.glorious.model.entity.base.Storehouse;
import com.glorious.model.entity.sys.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SysAdministratorService {

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public User buildAdministrator(String username, String password, Optional<Storehouse> storehouseOptional) {

        User user = new User();
        user.setEmail(username);
        user.setUsername(username);
        storehouseOptional.ifPresent(s -> user.setStorehouse_sql_id(s.getId()));

        user.setIs_admin(EnumBoolean.TRUE.getValue());
        user.setPassword(passwordEncoder.encode(password));

        return user;
    }
}
