package cn.footman.service;

import cn.footman.bean.T_MALL_PRODUCT;
import cn.footman.bean.T_MALL_SKU;
import cn.footman.bean.T_MALL_SKU_ATTR_VALUE;

import java.util.List;

/**
 * @author footman77
 * @create 2018-12-07 0:45
 */
public interface SkuService {
    void save_sku(T_MALL_SKU sku, T_MALL_PRODUCT spu, List<T_MALL_SKU_ATTR_VALUE> list_attr);
}
