package cn.footman.controller;

import cn.footman.bean.*;
import cn.footman.service.AttrService;
import cn.footman.service.ListService;
import cn.footman.util.JedisPoolUtils;
import cn.footman.util.MyCacheUtil;
import cn.footman.util.MyJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author footman77
 * @create 2018-12-12 11:29
 */
@Controller
public class ListController {

    @Autowired
    private ListService listService;

    @Autowired
    private AttrService attrService;

    @RequestMapping("getListByAttr")
    public String getListByAttr(MODEL_T_MALL_SKU_ATTR_VALUE list_attr, int flbh2, ModelMap map){

        List<OBJECT_T_MALL_SKU> list_sku = new ArrayList<>();

        List<T_MALL_SKU_ATTR_VALUE> list_attr1 = list_attr.getList_attr();
        String[] keys = new String[list_attr1.size()];

        for(int i = 0; i < list_attr1.size(); i++){
            keys[i] = "attr_"+flbh2+"_"+ list_attr1.get(i).getShxm_id() +"_"+ list_attr1.get(i).getShxzh_id();
        }

        String k0 = MyCacheUtil.interKeys(keys);

        list_sku = MyCacheUtil.getList(k0, OBJECT_T_MALL_SKU.class);
        if(list_sku == null || list_sku.size() == 0){
            //redis中没有查到
            list_sku = listService.getListByAttr(list_attr.getList_attr(),flbh2);
            //再将list_sku中的数据同步到redis中


        }


        map.put("list_sku",list_sku);
        return "skuList";
    }


    @RequestMapping("goto_list")
    public String goto_list(int flbh2,ModelMap map){
        //flbh2属性的集合
        List<OBJECT_T_MALL_ATTR> list_attr = attrService.get_attr_list(flbh2);
        //flbh2商品列表
        List<OBJECT_T_MALL_SKU> list_sku = new ArrayList<>();

        //通过redis查数据
        String key = "class_2_"+flbh2;
        list_sku = MyCacheUtil.getList(key,OBJECT_T_MALL_SKU.class);

        if(list_sku == null || list_sku.size() == 0){
            //redis中没有查到
            list_sku = listService.getListByFlbh2(flbh2);
            //再将list_sku中的数据同步到redis中
            MyCacheUtil.addList(key,list_sku);

        }



        map.put("flbh2",flbh2);
        map.put("list_attr",list_attr);
        map.put("list_sku",list_sku);
        return "list";

    }

}
