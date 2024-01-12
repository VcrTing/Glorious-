package com.glorious.system.restock.service.implExtra;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.glorious.model.entity.restock.Broken;
import com.glorious.model.mapper.restock.BrokenMapper;
import com.glorious.utils.utils.basic.QTypeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BrokenServiceImplExtra extends ServiceImpl<BrokenMapper, Broken>{

    /**
    * 通过电邮
    */
    public List<Broken> byProduct(Long productID) {
        if (!QTypeUtil.isLong(productID)) return null;
        return this.lambdaQuery().eq(Broken::getProduct_sql_id, productID).list();
    }

}
