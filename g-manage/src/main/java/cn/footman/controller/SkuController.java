package cn.footman.controller;

import cn.footman.bean.MODEL_T_MALL_SKU_ATTR_VALUE;
import cn.footman.bean.OBJECT_T_MALL_ATTR;
import cn.footman.bean.T_MALL_PRODUCT;
import cn.footman.bean.T_MALL_SKU;
import cn.footman.service.AttrService;
import cn.footman.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author footman77
 * @create 2018-12-06 14:35
 */
@Controller
public class SkuController {

    @Autowired
    private AttrService attrService;

    @Autowired
    private SkuService skuService;


    @RequestMapping("save_sku")
    public ModelAndView save_sku(T_MALL_SKU sku,MODEL_T_MALL_SKU_ATTR_VALUE list_attr, T_MALL_PRODUCT spu){

        skuService.save_sku(sku,spu,list_attr.getList_attr());

        ModelAndView modelAndView = new ModelAndView("redirect:goto_sku_add.do");
        modelAndView.addObject("flbh1",spu.getFlbh1());
        modelAndView.addObject("flbh2",spu.getFlbh2());

        return modelAndView;


    }


    @RequestMapping("goto_sku_add")
    public String goto_sku_add(int flbh1,int flbh2,ModelMap map){

        //加载属性和属性值列表
        List<OBJECT_T_MALL_ATTR> list_attr = attrService.get_attr_list(flbh2);


        map.put("flbh1",flbh1);
        map.put("flbh2",flbh2);
        map.put("list_attr",list_attr);

        return "skuAdd";
    }
}
