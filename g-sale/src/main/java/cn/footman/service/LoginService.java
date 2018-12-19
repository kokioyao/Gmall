package cn.footman.service;

import cn.footman.bean.T_MALL_USER_ACCOUNT;
import cn.footman.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author footman77
 * @create 2018-12-11 0:23
 */

public interface LoginService {


    public T_MALL_USER_ACCOUNT login(T_MALL_USER_ACCOUNT user);

}
