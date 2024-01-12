package com.glorious.system.backend.service.implView;

import com.glorious.model.entity.custom.Customer;
import com.glorious.model.vo.custom.CustomerLevelView;
import com.glorious.model.vo.custom.CustomerView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class CustomerServiceImplView {

    @Autowired
    CustomerLevelServiceImplView customerLevelServiceImplExtra;

    /**
    * entities TO view objects
    * @params
    * @return
    */
    public List<CustomerView> entitiesToVos(List<Customer> customers) {
        if (customers == null) return null;
        List<CustomerLevelView> customerLevelViewList = customerLevelServiceImplExtra.customerLevelViewList();
        // 序列化 多个
        return customers.stream()
                .map( customer -> CustomerView.byEntity(customer, customerLevelViewList) )
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * entity TO view object
     *
     */
    public CustomerView entityToVo(Customer customer) {
        // 序列化 单个
        return (customer == null) ? null : CustomerView.byEntity(customer, customerLevelServiceImplExtra.customerLevelViewList());
    }
}
