package com.glorious.model.vo.base;

import com.baomidou.mybatisplus.annotation.TableName;
import com.glorious.model.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class StorehouseView {
    private Long id;

    private String name;

    private String phone_no;

    private String facebook;

    private String contact_person;

    private String address;

    private String remark;
}