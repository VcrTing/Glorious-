package com.glorious.model.vo.sys;

import com.glorious.model.entity.base.Storehouse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoView {

    private Boolean isAdmin;

    private Storehouse storehouse;
}
