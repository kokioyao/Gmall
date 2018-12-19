package cn.footman.controller;

import cn.footman.bean.OBJECT_T_MALL_ATTR;
import cn.footman.bean.OBJECT_T_MALL_SKU;
import cn.footman.service.AttrService;
import cn.footman.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author footman77
 * @create 2018-12-10 15:35
 */
@Controller
public class IndexController {

    @Autowired
    private AttrService attrService;

    @Autowired
    private ListService listService;


    @RequestMapping("orderError")
    public String orderError(){
        return "orderErr";
    }


    @RequestMapping("goto_logout")
    public String goto_logout(HttpSession session){

        session.invalidate();
        return "redirect:/goto_login.do";
    }




    @RequestMapping("goto_login")
    public String goto_login(){
        return "login";
    }

    @RequestMapping("goto_login_check")
    public String goto_login_check(){

        return "loginCheck";
    }

    @RequestMapping("index")
    public String index(HttpServletRequest request, ModelMap map){
//        String yh_mch = "";
//        Cookie[] cookies = request.getCookies();
//        if(cookies != null && cookies.length > 0){
//            for(Cookie cookie : cookies){
//                String name = cookie.getName();
//                if(name.equals("yh_mch")){
//                    yh_mch = cookie.getValue();
//                }
//            }
//        }
//        map.put("yh_mch",yh_mch);
        return "index";
    }
}
