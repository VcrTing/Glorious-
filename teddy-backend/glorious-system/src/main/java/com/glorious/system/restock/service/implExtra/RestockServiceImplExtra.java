package com.glorious.system.restock.service.implExtra;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.glorious.model.entity.restock.Restock;
import com.glorious.model.mapper.restock.RestockMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RestockServiceImplExtra extends ServiceImpl<RestockMapper, Restock> {

    /**
    * 根据产品新增 入货
    */
    public List<Restock> byProduct(Long productID) {
        return this.lambdaQuery().eq(Restock::getProduct_sql_id, productID).list();
    }
}
