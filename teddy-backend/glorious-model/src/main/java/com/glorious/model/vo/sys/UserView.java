package com.glorious.model.vo.sys;

import com.glorious.model.define.enums.EnumBoolean;
import com.glorious.model.dto.sys.UserDto;
import com.glorious.model.entity.base.Storehouse;
import com.glorious.utils.utils.bean.QBeanUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Data
@NoArgsConstructor
public class UserView {

    private Long id;

    private String email;

    private String name;

    private String phone_no;

    private Boolean isAdmin;

    private Storehouse storehouse;

    public static UserView byDto(UserDto userDto) {
        Optional<UserView> ops = Optional.ofNullable(QBeanUtil.convert(userDto, UserView.class));
        ops.ifPresent(u -> {
            u.setIsAdmin(EnumBoolean.value(userDto.getIs_admin()));
        });
        return ops.orElse(new UserView());
    }

    public static List<UserView> byDtoList(List<UserDto> userDtoList) {
        return userDtoList == null ? null :
                userDtoList.stream()
                        .map(UserView::byDto)
                        .filter(dto -> dto.getId() != null).collect(Collectors.toList());
    }
}
