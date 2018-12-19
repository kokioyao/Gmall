package cn.footman.mapper;

import cn.footman.bean.T_MALL_ADDRESS;
import cn.footman.bean.T_MALL_USER_ACCOUNT;

import java.util.List;

/**
 * @author footman77
 * @create 2018-12-14 14:34
 */
public interface AddressMapper {
    T_MALL_ADDRESS selectAddress(int address_id);

    List<T_MALL_ADDRESS> selectAddresses(T_MALL_USER_ACCOUNT user);
}
