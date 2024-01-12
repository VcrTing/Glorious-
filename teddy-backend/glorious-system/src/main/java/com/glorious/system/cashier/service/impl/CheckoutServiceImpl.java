package com.glorious.system.cashier.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.glorious.common.exception.QLogicException;
import com.glorious.common.mvc.AjaxResult;
import com.glorious.framework.model.auth.AuthUser;
import com.glorious.framework.component.tools.SecurityTool;
import com.glorious.framework.module.dataset.BackendCacheService;
import com.glorious.framework.module.dataset.ProductCacheService;
import com.glorious.framework.module.dataset.UserCacheService;
import com.glorious.framework.module.deal.OrderDealService;
import com.glorious.model.entity.base.Storehouse;
import com.glorious.model.entity.custom.Customer;
import com.glorious.model.entity.order.Order;
import com.glorious.model.entity.order.product.OrderProduct;
import com.glorious.model.entity.product.Product;
import com.glorious.model.entity.product.ProductOfVariationAndStorehouse;
import com.glorious.model.entity.product.Variation;
import com.glorious.model.entity.sys.User;
import com.glorious.model.form.order.CheckoutForm;
import com.glorious.model.form.order.checkout.InnerFormCheckoutProduct;
import com.glorious.model.mapper.base.StorehouseMapper;
import com.glorious.model.mapper.custom.CustomerLevelMapper;
import com.glorious.model.mapper.custom.CustomerMapper;
import com.glorious.model.mapper.order.OrderMapper;
import com.glorious.model.mapper.product.ProductMapper;
import com.glorious.model.mapper.sys.UserMapper;
import com.glorious.system.cashier.service.CheckoutService;
import com.glorious.system.product.service.impl.ProductOfVariationAndStorehouseServiceImpl;
import com.glorious.system.product.service.implExtra.ProductOfVariationAndStorehouseServiceImplExtra;
import com.glorious.utils.utils.basic.QSumUtil;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class CheckoutServiceImpl extends ServiceImpl<OrderMapper, Order> implements CheckoutService {

    @Autowired
    SecurityTool securityTool;

    @Autowired
    UserMapper userMapper;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    CustomerMapper customerMapper;

    @Autowired
    StorehouseMapper storehouseMapper;

    @Autowired
    CustomerLevelMapper customerLevelMapper;

    @Autowired
    ProductOfVariationAndStorehouseServiceImpl productOfVariationAndStorehouseServiceImpl;

    @Autowired
    ProductOfVariationAndStorehouseServiceImplExtra productOfVariationAndStorehouseServiceImplExtra;

    @Autowired
    OrderDealService orderDealService;

    @Autowired
    UserCacheService userCacheService;

    @Autowired
    BackendCacheService backendCacheService;

    @Autowired
    ProductCacheService productCacheService;

    final static Object lock = new Object();

    /**
    * 扣除 需购买的 库存
    */
    @Override
    public Object deductInventory(CheckoutForm form, Long storehouseID) {

        // 提起下单产品
        List<InnerFormCheckoutProduct> products = form.getOrdered_product();
        if (products == null || products.size() <= 0) return "未发现需购买的产品，请勾选购物产品";

        // 查询 订单某项库存是否足够
        for (InnerFormCheckoutProduct op: products) {
            if (op.getQuantity() <= 0) return "某项产品需购买的数量小于一";

            // 查询 库存表
            ProductOfVariationAndStorehouse pvs = productOfVariationAndStorehouseServiceImpl.fetch(
                    op.getProduct(),
                    op.getVariation(),
                    storehouseID
            );
            // 检查 库存是否足够
            // 购买之后的 结果库存
            Integer inventory = QSumUtil.sub(pvs.getQuantity(), op.getQuantity());
            if (inventory <= 0) return "产品库存不足，无法生成订单，请添加库存后再下单";

            // 扣除库存
            boolean isOK = productOfVariationAndStorehouseServiceImplExtra.updQuantity(
                    pvs,
                    inventory
            );
            if (!isOK) return "因网络波动，购买的产品库存同步失败";
        }
        return true;
    }

    /**
    * 下单
    */
    @Override
    public Object posOrder(CheckoutForm form, User cashier, Customer customer) {

        // 要下单的产品
        List<InnerFormCheckoutProduct> checkoutProducts = form.getOrdered_product();

        // 给付款产品 连接 产品数据
        for (InnerFormCheckoutProduct product: checkoutProducts) {
            // 必须 查询 最新 产品数据
            Product p = productMapper.selectById(product.getProduct());
            if (p == null) return "下单产品数据未能找到，无法下单";
            // ProductEntity 不能为空
            product.setProductEntity(p);
        }

        // 查询产品
        Order entity = CheckoutForm.toEntity(form, cashier, customer);

        // 加入樣式數據
        List<OrderProduct> orderProductList = JSONUtil.toList(entity.getOrdered_product(), OrderProduct.class);
        for (OrderProduct op: orderProductList) {
            // 從 緩存里 獲取，不處理 樣式 獲取 失敗 問題
            Variation variation = productCacheService.getVariation(op.getVariation_sql_id());
            op.setVariation(variation);
        }
        entity.setOrdered_product(JSONUtil.toJsonStr(orderProductList));

        // 更新 訂單 時間
        entity.setOrder_date(new Date());

        // 儲存
        if (!this.save(entity)) return "因网络波动，订单生成失败，本次下单失效";

        // 返回订单
        return entity;
    }

    /**
    * 回滚 事务，代码内不允许存在一丝异常，否则就回滚
    */
    @Transactional(rollbackFor = Exception.class)
    public Order checkoutTrans(CheckoutForm form, User cashier, Customer customer, Long storehouseID) {

        // 对订单库存的 处理操作，多个线程，只允许存在一个
        synchronized (lock) {
            // 扣除 库存
            Object resultInventory = deductInventory(form, storehouseID);
            // 处理
            if (resultInventory instanceof String) orderDealService.dealCheckoutError(resultInventory.toString(), customer);
        }

        // 结算 金额
        Object resultOrder = posOrder(form, cashier, customer);
        // 处理
        if (resultOrder instanceof String) orderDealService.dealCheckoutError(resultOrder.toString(), customer);

        // 返回
        return (resultOrder instanceof Order) ? (Order) resultOrder : null;
    }

    /**
    * 结算
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult checkout(CheckoutForm form) {

        // 检测 收银员
        AuthUser authUser = securityTool.getPrincipal();
        if (authUser == null || authUser.getUserId() == null) return AjaxResult.error("未找到收银员信息，请重试 结算！！！");
        User cashier = userCacheService.getUser(authUser.getUserId()); // userMapper.selectById(authUser.getUserId());
        if (cashier == null) return AjaxResult.error("未找到收银员数据信息");

        // 检测 仓库
        Long storehouseID = cashier.getStorehouse_sql_id();
        if (storehouseID == null) return AjaxResult.error("该收银员未绑定仓库信息，请先绑定仓库后再结算订单");
        Storehouse storehouse = backendCacheService.getStorehouse(storehouseID); // storehouseMapper.selectById(storehouseID);
        if (storehouse == null) return AjaxResult.error("未找到收银仓库的数据信息");

        // 检测 顾客、顾客等级，订单可以没客户，客户可以没等级
        Customer customer = null;
        if (form.getMember() != null) {
            customer = customerMapper.selectById(form.getMember());
            if (customer.getCustomer_level_sql_id() == null) {
                // 该 用户 没有 会员 等级，则没有会员
            }
        }

        // 进入回滚事务，进行订单下单
        Order resultOrder = checkoutTrans(form, cashier, customer, storehouseID);

        // 返回 成功 结果
        return orderDealService.dealCheckoutSuccess(resultOrder, customer);
    }
}
