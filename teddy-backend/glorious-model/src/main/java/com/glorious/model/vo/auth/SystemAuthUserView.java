package com.glorious.model.vo.auth;

import com.glorious.model.entity.sys.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;

@Data
public class SystemAuthUserView {

    private String jwt;

    private HashMap<String, Object> user;

    public SystemAuthUserView(String jwt, Long id, String email) {
        HashMap<String, Object> res = new HashMap<>();
        res.put("id", id);
        res.put("email", email);
        res.put("username", email);

        this.jwt = jwt;
        this.user = res;
    }
}
