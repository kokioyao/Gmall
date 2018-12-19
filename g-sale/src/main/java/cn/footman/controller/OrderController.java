package cn.footman.controller;

import cn.footman.bean.*;
import cn.footman.exception.OverSaleException;
import cn.footman.server.AddressServer;
import cn.footman.service.CartService;
import cn.footman.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author footman77
 * @create 2018-12-16 14:10
 */
@Controller
@SessionAttributes("order")
public class OrderController {


    @Resource
    private AddressServer addressServer;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @RequestMapping("goto_checkOrder")
    public String goto_checkOrder(HttpSession session, ModelMap map){
        T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user");
        List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<>();

        if(user == null){
            //没有登陆，则需要跳转到登陆页面
            return "redirect:/goto_login_check.do";
        }else {
            //从session中获得购物车数据
            list_cart = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart_session");

            //根据选中状态获得库存地址信息
            Set<String> set_kcdz = new HashSet<>();
            for(int i = 0; i < list_cart.size(); i++){
                if(list_cart.get(i).getShfxz().equals("1")){
                    set_kcdz.add(list_cart.get(i).getKcdz());
                }
            }


            OBJECT_T_MALL_ORDER order = new OBJECT_T_MALL_ORDER();
            order.setYh_id(user.getId());
            order.setJdh(1);
            order.setZje(getSum(list_cart));

            List<OBJECT_T_MALL_FLOW> list_flow = new ArrayList<>();

            //根据库存地址信息进行拆单
            Iterator<String> iterator = set_kcdz.iterator();
            while (iterator.hasNext()){
                String kcdz = iterator.next();
                OBJECT_T_MALL_FLOW flow = new OBJECT_T_MALL_FLOW();
                flow.setMqdd("商品未出库");
                flow.setPsfsh("哪都通");
                flow.setYh_id(user.getId());

                List<T_MALL_ORDER_INFO> list_info = new ArrayList<>();
                //循环购物车，将购物车中的对象，转化成订单info
                for(int i = 0; i < list_cart.size(); i++){
                    if(list_cart.get(i).getShfxz().equals("1")
                            &&list_cart.get(i).getKcdz().equals(kcdz)){
                        T_MALL_SHOPPINGCAR cart = list_cart.get(i);
                        T_MALL_ORDER_INFO orderInfo = new T_MALL_ORDER_INFO();

                        orderInfo.setGwch_id(cart.getId());
                        orderInfo.setShp_tp(cart.getShp_tp());
                        orderInfo.setSku_id(cart.getSku_id());
                        orderInfo.setSku_jg(cart.getSku_jg());
                        orderInfo.setSku_kcdz(cart.getKcdz());
                        orderInfo.setSku_mch(cart.getSku_mch());
                        orderInfo.setSku_shl(cart.getTjshl());

                        list_info.add(orderInfo);
                    }
                }
                flow.setList_info(list_info);
                //送货清单对象放入list中
                list_flow.add(flow);
            }
            order.setList_flow(list_flow);
            map.put("order",order);
        }

        List<T_MALL_ADDRESS> addresses = null;
        try {
            addresses = addressServer.getAddresses(user);
        } catch (Exception e) {
            return "redirect:/orderError.do";
        }

        map.put("addresses",addresses);

        return "checkOrder";


    }



    @RequestMapping("save_order")
    public String save_order(@ModelAttribute("order") OBJECT_T_MALL_ORDER order,T_MALL_ADDRESS address,HttpSession session){
        T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user");

        //保存订单，先远程获得地址信息
        T_MALL_ADDRESS addr = addressServer.getAddress(address.getId());

        //保存订单
        orderService.save_order(order,addr);

        //重置同步session
        session.setAttribute("list_cart_session",cartService.selectListCartByUser(user));

        //重定向到支付服务
        return "redirect:/goto_pay.do ";

//        return "realPay";
    }


    //假装使用
    @RequestMapping("goto_pay")
    public String goto_pay(){
        //支付服务，假装使用
        return "pay";
    }


    @RequestMapping("pay_success")
    public String pay_success(@ModelAttribute("order") OBJECT_T_MALL_ORDER order){
        //支付成功
        try {
            orderService.pay_success(order);
        } catch (OverSaleException e) {
            e.printStackTrace();
            return "redirect:/order_fail.do";
        }
        //独立页面

        return "redirect:/order_success.do";
    }


    @RequestMapping("real_pay_success")
    @ResponseBody
    public String real_pay_success(@ModelAttribute("order") OBJECT_T_MALL_ORDER order){
        //支付成功
        try {
            orderService.pay_success(order);
        } catch (OverSaleException e) {
            e.printStackTrace();
            return "success";
        }
        //独立页面

        return "success";
    }


    @RequestMapping("order_success")
    public String order_success(){
        //支付成功

        return "orderSuccess";
    }

    @RequestMapping("order_fail")
    public String order_fail(){
        //支付失败

        return "orderFail";
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
}

