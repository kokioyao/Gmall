package cn.footman.server;

import cn.footman.bean.T_MALL_USER_ACCOUNT;

import javax.jws.WebService;

/**
 * @author footman77
 * @create 2018-12-14 13:30
 */
@WebService
public interface LoginServer {
    public String login(T_MALL_USER_ACCOUNT user);

    public String login2(T_MALL_USER_ACCOUNT user);

}
