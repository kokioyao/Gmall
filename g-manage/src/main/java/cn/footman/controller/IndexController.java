package cn.footman.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author footman77
 * @create 2018-12-04 0:50
 */
@Controller
public class IndexController {

    @RequestMapping("goto_sku")
    public String goto_sku(){
        return "sku";
    }


    @RequestMapping("/index")
    public String index(String url, String title,ModelMap map){
        map.put("title",title);
        map.put("url",url);
        return "main";
    }

    @RequestMapping("goto_spu")
    public String goto_spu(){
        return "spu";
    }


    @RequestMapping("goto_attr")
    public String goto_attr(){
        return "attr";
    }



}
