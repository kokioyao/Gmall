package cn.footman.server;

import cn.footman.bean.T_MALL_ADDRESS;
import cn.footman.bean.T_MALL_USER_ACCOUNT;

import javax.jws.WebService;
import java.util.List;

/**
 * @author footman77
 * @create 2018-12-14 14:27
 */
@WebService
public interface AddressServer {

    T_MALL_ADDRESS getAddress(int address_id);
    List<T_MALL_ADDRESS> getAddresses(T_MALL_USER_ACCOUNT user);
}
