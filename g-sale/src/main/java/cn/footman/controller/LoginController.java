package cn.footman.controller;

import cn.footman.bean.T_MALL_SHOPPINGCAR;
import cn.footman.bean.T_MALL_USER_ACCOUNT;
import cn.footman.server.LoginServer;
import cn.footman.server.TestServer;
import cn.footman.service.CartService;
import cn.footman.service.LoginService;
import cn.footman.util.MyJsonUtil;
import cn.footman.util.MyPropertiesUtil;
import cn.footman.util.MyWsFactoryBean;
import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.jaxws.JaxWsPortProxyFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author footman77
 * @create 2018-12-10 16:56
 */
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private CartService cartService;

    @Resource
    private LoginServer loginServer;


//    @Resource
//    private TestServer testServer;

    @RequestMapping("login")
    public String login(@CookieValue(value = "list_cart_cookie", required = false) String list_cart_cookie,
                        HttpServletResponse response, T_MALL_USER_ACCOUNT user,
                        HttpSession session,String dataSource_type,@RequestParam(value = "redirect",required = false) String redirect){
//


        T_MALL_USER_ACCOUNT select_user = new T_MALL_USER_ACCOUNT();// loginService.login(user);
//        T_MALL_USER_ACCOUNT select_user = loginService.login(user);
        //登陆，调用远程接口

//        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
//        jaxWsProxyFactoryBean.setAddress(MyPropertiesUtil.getMyProperty("ws.properties","login_url"));
//        jaxWsProxyFactoryBean.setServiceClass(LoginServer.class);
//
//        LoginServer create = (LoginServer) jaxWsProxyFactoryBean.create();

        //这是调用user服务的代码，暂时注掉
        String loginJson = "";
        if("1".equals(dataSource_type)){
             loginJson = loginServer.login(user);
//             testServer.ping("hello");//用于安全测试，调用方法
        }else if("2".equals(dataSource_type)) {
             loginJson = loginServer.login2(user);
        }

        select_user = MyJsonUtil.json_to_object(loginJson,T_MALL_USER_ACCOUNT.class);

        if(select_user == null){
            return "redirect:/login.do";
        }else {
            session.setAttribute("user",select_user);

            Cookie cookie = null;
            try {
                cookie = new Cookie("yh_mch", URLEncoder.encode(select_user.getYh_mch(),"UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            cookie.setPath("/");
            cookie.setMaxAge(60*60*24);
            response.addCookie(cookie);

            //同步购物车
            mergeCart(list_cart_cookie,select_user,session,response);

        }

        if(StringUtils.isBlank(redirect)){
            return "redirect:/index.do";

        }else {
            return "redirect:/"+redirect;
        }

    }

    private void mergeCart(String list_cart_cookie, T_MALL_USER_ACCOUNT user,
                           HttpSession session,HttpServletResponse response) {
        List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<T_MALL_SHOPPINGCAR>();
        if(StringUtils.isNotBlank(list_cart_cookie)){
            List<T_MALL_SHOPPINGCAR> list_cart_db = cartService.selectListCartByUser(user);
            list_cart = MyJsonUtil.json_to_list(list_cart_cookie, T_MALL_SHOPPINGCAR.class);

            for(int i = 0; i < list_cart.size(); i++){

                T_MALL_SHOPPINGCAR cart = list_cart.get(i);
                //从cookie中拿到的cart是没有用户id的，这边添加
                cart.setYh_id(user.getId());
                //查看是否已经存在
                boolean b = cartService.ifCartExist(cart);

                if(b){
                    //已经存在，则更新
                    for (int j = 0; j < list_cart_db.size(); j++) {
                       if(list_cart_db.get(j).getSku_id() == cart.getSku_id()){
                           list_cart_db.get(j).setTjshl(list_cart_db.get(j).getTjshl() + cart.getTjshl());
                           list_cart_db.get(j).setHj(list_cart_db.get(j).getTjshl() * cart.getSku_jg());
                           cartService.updateCart(list_cart_db.get(j));
                       }
                    }
                }else {
                    //不存在，则新添加
                    cartService.addCart(cart);
                }
            }

        }
        //同步session，清空cookie
        session.setMaxInactiveInterval(7*60*60*24);
        session.setAttribute("list_cart_session",cartService.selectListCartByUser(user));

        response.addCookie(new Cookie("list_cart_cookie",""));



    }


    private boolean if_new_cart(List<T_MALL_SHOPPINGCAR> list_cart, T_MALL_SHOPPINGCAR cart) {
        boolean b = true;
        for (int i = 0; i < list_cart.size(); i++) {
            if (list_cart.get(i).getSku_id() == cart.getSku_id()) {
                b = false;
            }
        }
        return b;
    }

}
