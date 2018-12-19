package cn.footman.service;

import cn.footman.bean.T_MALL_USER_ACCOUNT;
import cn.footman.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author footman77
 * @create 2018-12-11 0:24
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;


    @Override
    public T_MALL_USER_ACCOUNT login(T_MALL_USER_ACCOUNT user) {
        return loginMapper.select_user(user);
    }
}
