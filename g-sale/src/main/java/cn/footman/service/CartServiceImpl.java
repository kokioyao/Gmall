package cn.footman.service;

import cn.footman.bean.T_MALL_SHOPPINGCAR;
import cn.footman.bean.T_MALL_USER_ACCOUNT;
import cn.footman.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author footman77
 * @create 2018-12-13 0:35
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Override
    public void addCart(T_MALL_SHOPPINGCAR cart) {
        cartMapper.saveCart(cart);
    }

    @Override
    public void updateCart(T_MALL_SHOPPINGCAR t_mall_shoppingcar) {
        cartMapper.updateCart(t_mall_shoppingcar);
    }

    @Override
    public boolean ifCartExist(T_MALL_SHOPPINGCAR cart) {
        boolean b = false;
        int i = cartMapper.selectCartExists(cart);
        if(i > 0){
            b = true;
        }
        return b;
    }

    @Override
    public List<T_MALL_SHOPPINGCAR> selectListCartByUser(T_MALL_USER_ACCOUNT user) {
        return cartMapper.selectListCartByUser(user);
    }
}
