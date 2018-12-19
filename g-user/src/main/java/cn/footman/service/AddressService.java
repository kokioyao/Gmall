package cn.footman.service;

import cn.footman.bean.T_MALL_ADDRESS;
import cn.footman.bean.T_MALL_USER_ACCOUNT;

import java.util.List;

/**
 * @author footman77
 * @create 2018-12-14 14:32
 */
public interface AddressService {
    T_MALL_ADDRESS getAddress(int address_id);

    List<T_MALL_ADDRESS> getAddresses(T_MALL_USER_ACCOUNT user);
}
