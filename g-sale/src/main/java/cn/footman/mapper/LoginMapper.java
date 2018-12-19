package cn.footman.mapper;

import cn.footman.bean.T_MALL_USER_ACCOUNT;

/**
 * @author footman77
 * @create 2018-12-11 0:19
 */
public interface LoginMapper {

    public T_MALL_USER_ACCOUNT select_user(T_MALL_USER_ACCOUNT user);
}
