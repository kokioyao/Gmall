package cn.footman.service;

import cn.footman.bean.MODEL_T_MALL_SKU_ATTR_VALUE;
import cn.footman.bean.T_MALL_PRODUCT;
import cn.footman.bean.T_MALL_SKU;
import cn.footman.bean.T_MALL_SKU_ATTR_VALUE;
import cn.footman.mapper.SkuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author footman77
 * @create 2018-12-07 0:45
 */
@Service
public class SkuServiceImpl implements SkuService {

    @Autowired
    private SkuMapper skuMapper;

    @Override
    public void save_sku(T_MALL_SKU sku, T_MALL_PRODUCT spu, List<T_MALL_SKU_ATTR_VALUE> list_attr) {

        sku.setShp_id(spu.getId());
        skuMapper.insert_sku(sku);

        Map<Object, Object> map = new HashMap<>();
        map.put("shp_id",spu.getId());
        map.put("sku_id",sku.getId());
        map.put("list_av",list_attr);

        skuMapper.insert_sku_av(map);

    }
}
