package cn.footman.server;

import cn.footman.bean.T_MALL_USER_ACCOUNT;
import cn.footman.service.LoginService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;

/**
 * @author footman77
 * @create 2018-12-14 13:31
 */
public class LoginServerImpl implements LoginServer {

    @Autowired
    private LoginService loginService;

    @Override
    @Path("login")
    @GET
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
    public String login(@BeanParam T_MALL_USER_ACCOUNT user) {
        T_MALL_USER_ACCOUNT user_account = loginService.login(user);
        Gson gson = new Gson();
        return gson.toJson(user_account);

    }


    @Override
    @Path("login")
    @GET
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
    public String login2(@BeanParam T_MALL_USER_ACCOUNT user) {
        T_MALL_USER_ACCOUNT user_account = loginService.login2(user);
        Gson gson = new Gson();
        return gson.toJson(user_account);

    }
}
