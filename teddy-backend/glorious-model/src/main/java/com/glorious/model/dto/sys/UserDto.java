package com.glorious.model.dto.sys;

import com.glorious.model.entity.base.Storehouse;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

    private Long id;

    private String email;

    private String name;

    private String phone_no;

    private Short is_admin;

    private Storehouse storehouse;
}
