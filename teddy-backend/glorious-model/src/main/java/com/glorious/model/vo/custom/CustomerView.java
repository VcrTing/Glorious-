package com.glorious.model.vo.custom;

import com.glorious.model.define.enums.EnumSex;
import com.glorious.model.entity.custom.Customer;
import com.glorious.utils.utils.basic.QTypeUtil;
import com.glorious.utils.utils.bean.QBeanUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerView {

    private Long id;

    private String name;

    private String email;

    private String phone_no;

    private String remarks;

    private Date create_date;

    private Date birthdate;

    private EnumSex sex;

    private String member_id;

    private String address;

    private Long member_level_id;

    private CustomerLevelView member_level;

    public static CustomerView byEntity(Customer entity, List<CustomerLevelView> customerLevelViewList) {
        Optional<CustomerView> ops = Optional.ofNullable(QBeanUtil.convert(entity, CustomerView.class));
        ops.ifPresent( one -> {
            if (QTypeUtil.isLong(entity.getId())) one.setId(entity.getId());

            if (QTypeUtil.isLong(entity.getCustomer_level_sql_id())) {
                one.setMember_level_id(entity.getCustomer_level_sql_id());

                // 根据 List<CustomerLevelView> 组装 CustomerLevelView
                if (customerLevelViewList != null) {
                    customerLevelViewList.stream()
                            .filter(clv -> (one.getMember_level_id().equals(clv.getId())))
                            .findFirst()
                            .ifPresent(one::setMember_level);
                }
            }
        });

        return ops.orElse(new CustomerView());
    }
}
