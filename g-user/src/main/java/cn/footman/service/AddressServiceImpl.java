package cn.footman.service;

import cn.footman.bean.T_MALL_ADDRESS;
import cn.footman.bean.T_MALL_USER_ACCOUNT;
import cn.footman.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author footman77
 * @create 2018-12-14 14:32
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public T_MALL_ADDRESS getAddress(int address_id) {
        return addressMapper.selectAddress(address_id);
    }

    @Override
    public List<T_MALL_ADDRESS> getAddresses(T_MALL_USER_ACCOUNT user) {
        return addressMapper.selectAddresses(user);
    }
}
