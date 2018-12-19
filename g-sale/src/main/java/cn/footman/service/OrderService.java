package cn.footman.service;

import cn.footman.bean.OBJECT_T_MALL_ORDER;
import cn.footman.bean.T_MALL_ADDRESS;
import cn.footman.exception.OverSaleException;

/**
 * @author footman77
 * @create 2018-12-16 23:30
 */
public interface OrderService {

    void save_order(OBJECT_T_MALL_ORDER order, T_MALL_ADDRESS addr);

    void pay_success(OBJECT_T_MALL_ORDER order) throws OverSaleException;
}
