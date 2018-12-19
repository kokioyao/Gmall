package cn.footman.controller;

import cn.footman.bean.T_MALL_SHOPPINGCAR;
import cn.footman.bean.T_MALL_USER_ACCOUNT;
import cn.footman.service.CartService;
import cn.footman.util.MyJsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author footman77
 * @create 2018-12-12 22:39
 */
@Controller
public class CartController {

    @Autowired
    private CartService cartService;








    @RequestMapping("changeChecked")
    public String changeChecked(@CookieValue(value = "list_cart_cookie", required = false) String list_cart_cookie,
                                HttpSession session,T_MALL_SHOPPINGCAR cart, ModelMap map,HttpServletResponse response){

        T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user");
        List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<>();

        if(user == null){
            //没有登陆，改变cookie中的内容
            list_cart = MyJsonUtil.json_to_list(list_cart_cookie, T_MALL_SHOPPINGCAR.class);
        }else {
            //已经登陆，改变数据库中的内容
            list_cart = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart_session");
        }

        for(int i = 0; i < list_cart.size(); i++){
            if(list_cart.get(i).getSku_id() == cart.getSku_id()){
                list_cart.get(i).setShfxz(cart.getShfxz());
                if(user == null){
                    //覆盖cookie
                    Cookie cookie = new Cookie("list_cart_cookie", MyJsonUtil.list_to_json(list_cart));
                    cookie.setMaxAge(60 * 60 * 24);
                    response.addCookie(cookie);
                }else {
                    cartService.updateCart(list_cart.get(i));
                }
            }
        }



        map.put("list_cart",list_cart);
        map.put("sum",getSum(list_cart));

        return "cartListInner";
    }




    @RequestMapping("gotoCartList")
    public String gotoCartList(@CookieValue(value = "list_cart_cookie", required = false) String list_cart_cookie,
                               HttpSession session, ModelMap map){

        //查询cookie或这sessison中的cartlist的内容
        T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user");
        List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<>();

        if(user == null){
            //用户没有登陆，则从cookie中获取购物车数据

            list_cart = MyJsonUtil.json_to_list(list_cart_cookie, T_MALL_SHOPPINGCAR.class);

        }else {
            list_cart = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart_session");
        }




        map.put("list_cart",list_cart);
        map.put("sum",getSum(list_cart));

        return "cartList";
    }

    private BigDecimal getSum(List<T_MALL_SHOPPINGCAR> list_cart) {
        BigDecimal sum = new BigDecimal("0");

        if(list_cart != null){

            for(int i = 0; i < list_cart.size(); i++){
                if("1".equals(list_cart.get(i).getShfxz())){
                    sum = sum.add(new BigDecimal(list_cart.get(i).getHj() + ""));
                }
            }
        }


        return sum;
    }


    @RequestMapping("getCartList")
    public String getCartList(@CookieValue(value = "list_cart_cookie", required = false) String list_cart_cookie,
                              HttpSession session, ModelMap map){
        //查询cookie或这sessison中的cartlist的内容
        T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user");
        List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<>();

        if(user == null){
            //用户没有登陆，则从cookie中获取购物车数据

            list_cart = MyJsonUtil.json_to_list(list_cart_cookie, T_MALL_SHOPPINGCAR.class);

        }else {
            list_cart = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart_session");
        }

        map.put("list_cart",list_cart);

        return "miniCartList";
    }



    @RequestMapping("add_cart")
    public String add_cart(@CookieValue(value = "list_cart_cookie", required = false) String list_cart_cookie,
                           T_MALL_SHOPPINGCAR cart, HttpServletResponse response, HttpSession session) {

        int yh_id = cart.getYh_id();

        List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<>();


        //添加购物车
        if (yh_id == 0) {

            //用户未登录，操作cookie
            if (StringUtils.isBlank(list_cart_cookie)) {
                //为空，则直接新添加cookie
                list_cart.add(cart);


            } else {

                list_cart = MyJsonUtil.json_to_list(list_cart_cookie, T_MALL_SHOPPINGCAR.class);
                //cookie不为空，则判断cookie中的内容是否为同一种sku

                boolean b = if_new_cart(list_cart, cart);

                if (b) {
                    //为新的值
                    list_cart.add(cart);
                } else {
                    //更新
                    for (int i = 0; i < list_cart.size(); i++) {
                        if (list_cart.get(i).getSku_id() == cart.getSku_id()) {
                            list_cart.get(i).setTjshl(list_cart.get(i).getTjshl() + cart.getTjshl());
                            list_cart.get(i).setHj(list_cart.get(i).getTjshl() * list_cart.get(i).getSku_jg());

                        }
                    }
                }


            }
            //覆盖cookie
            Cookie cookie = new Cookie("list_cart_cookie", MyJsonUtil.list_to_json(list_cart));
            cookie.setMaxAge(60 * 60 * 24);
            response.addCookie(cookie);


        } else {
            list_cart = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart_session");

            //用户登陆，操作db

            boolean b = cartService.ifCartExist(cart);
            if (!b) {
                //添加
                cartService.addCart(cart);
                if (list_cart == null || list_cart.isEmpty()) {
                    list_cart = new ArrayList<T_MALL_SHOPPINGCAR>();
                    list_cart.add(cart);
                    session.setMaxInactiveInterval(7*60*60*24);
                    session.setAttribute("list_cart_session", list_cart);
                }else {
                    list_cart.add(cart);

                }
            } else {
                //更新
                //同步session
                for (int i = 0; i < list_cart.size(); i++) {
                    if (list_cart.get(i).getSku_id() == cart.getSku_id()) {
                        list_cart.get(i).setTjshl(list_cart.get(i).getTjshl() + cart.getTjshl());
                        list_cart.get(i).setHj(list_cart.get(i).getTjshl() * list_cart.get(i).getSku_jg());
                        //已经存在，更新
                        cartService.updateCart(list_cart.get(i));
                    }
                }

            }


        }

        return "redirect:/cart_success.do";
    }


    @RequestMapping("cart_success")
    public String cart_success() {

        return "cartSuccess";
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


//  list_cart = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart_session");
//        //用户登陆，操作db
//        if(list_cart == null || list_cart.isEmpty()){
//        //db中没有数据,则需要进行插入
//        cartService.addCart(cart);
//        //同步session
//        list_cart = new ArrayList<T_MALL_SHOPPINGCAR>();
//        list_cart.add(cart);
//        session.setAttribute("list_cart_session",list_cart);
//
//        }else {
//        boolean b = if_new_cart(list_cart,cart);
//        if(b){
//        //新
//        cartService.addCart(cart);
//        //同步session
//        list_cart.add(cart);
//        }else {
//
//        //同步session
//        for(int i = 0; i < list_cart.size(); i++){
//        if(list_cart.get(i).getSku_id() == cart.getSku_id()){
//        list_cart.get(i).setTjshl(list_cart.get(i).getTjshl() + cart.getTjshl());
//        list_cart.get(i).setHj(list_cart.get(i).getTjshl() * list_cart.get(i).getSku_jg());
//        //已经存在，更新
//        cartService.updateCart(list_cart.get(i));
//        }
//        }
//
//        }
//        }
