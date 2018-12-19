package cn.footman.server;

import cn.footman.bean.T_MALL_ADDRESS;
import cn.footman.bean.T_MALL_USER_ACCOUNT;
import cn.footman.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author footman77
 * @create 2018-12-14 14:31
 */
public class AddressServerImpl implements AddressServer {

    @Autowired
    private AddressService addressService;

    @Override
    public T_MALL_ADDRESS getAddress(int address_id) {
        return addressService.getAddress(address_id);
    }

    @Override
    public List<T_MALL_ADDRESS> getAddresses(T_MALL_USER_ACCOUNT user) {
        return addressService.getAddresses(user);
    }
}
