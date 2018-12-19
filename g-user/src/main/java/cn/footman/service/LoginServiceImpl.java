package cn.footman.service;

import cn.footman.bean.T_MALL_USER_ACCOUNT;
import cn.footman.mapper.LoginMapper;
import cn.footman.util.MyRoutingDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author footman77
 * @create 2018-12-14 13:32
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public T_MALL_USER_ACCOUNT login(T_MALL_USER_ACCOUNT user) {
        MyRoutingDataSource.setKey("1");
        return loginMapper.select_user(user);
    }


    @Override
    public T_MALL_USER_ACCOUNT login2(T_MALL_USER_ACCOUNT user) {
        MyRoutingDataSource.setKey("2");
        return loginMapper.select_user(user);
    }
}
