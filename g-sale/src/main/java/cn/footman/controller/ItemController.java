package cn.footman.controller;

import cn.footman.bean.DETAIL_T_MALL_SKU;
import cn.footman.bean.T_MALL_SKU;
import cn.footman.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author footman77
 * @create 2018-12-12 19:42
 */
@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("gotoSkuDetail")
    public String gotoSkuDetail(int sku_id, int spu_id, ModelMap map){
        //查询商品的详细信息对象
        DETAIL_T_MALL_SKU obj_sku = itemService.getSkuDetail(sku_id);

        //查询同spu下的相关的其他sku信息
        List<T_MALL_SKU> list_sku = itemService.getListSku(spu_id);


        map.put("obj_sku",obj_sku);
        map.put("list_sku",list_sku);
        return "skuDetail";
    }


}

