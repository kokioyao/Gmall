package cn.footman.service;

import cn.footman.bean.T_MALL_SHOPPINGCAR;
import cn.footman.bean.T_MALL_USER_ACCOUNT;

import java.util.List;

/**
 * @author footman77
 * @create 2018-12-13 0:35
 */
public interface CartService {
    void addCart(T_MALL_SHOPPINGCAR cart);

    void updateCart(T_MALL_SHOPPINGCAR t_mall_shoppingcar);

    boolean ifCartExist(T_MALL_SHOPPINGCAR cart);

    List<T_MALL_SHOPPINGCAR> selectListCartByUser(T_MALL_USER_ACCOUNT user);
}
