package cn.footman.mapper;

import cn.footman.bean.T_MALL_SHOPPINGCAR;
import cn.footman.bean.T_MALL_USER_ACCOUNT;

import java.util.List;

/**
 * @author footman77
 * @create 2018-12-13 0:37
 */
public interface CartMapper {
    void saveCart(T_MALL_SHOPPINGCAR cart);

    void updateCart(T_MALL_SHOPPINGCAR t_mall_shoppingcar);

    int selectCartExists(T_MALL_SHOPPINGCAR cart);

    List<T_MALL_SHOPPINGCAR> selectListCartByUser(T_MALL_USER_ACCOUNT user);
}
