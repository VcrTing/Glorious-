package com.glorious.model.vo.product.product;

import com.glorious.model.entity.base.Storehouse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InnerViewStorehouse {

    private Long storehouse_id;
    private String storehouse_name;

    private String phone_no;
    private String storehouse_address;

    private List<InnerViewVariation> variation;

    public static InnerViewStorehouse byEntity(Storehouse storehouse, List<InnerViewVariation> variation) {
        InnerViewStorehouse res = new InnerViewStorehouse();
        res.setStorehouse_id(storehouse.getId());
        res.setPhone_no(storehouse.getPhone_no());
        res.setStorehouse_name(storehouse.getName());
        res.setStorehouse_address(storehouse.getAddress());
        res.setVariation(variation);
        return res;
    }
}
