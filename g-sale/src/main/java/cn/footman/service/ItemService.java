package cn.footman.service;

import cn.footman.bean.DETAIL_T_MALL_SKU;
import cn.footman.bean.T_MALL_SKU;

import java.util.List;

/**
 * @author footman77
 * @create 2018-12-12 19:46
 */
public interface ItemService {
    DETAIL_T_MALL_SKU getSkuDetail(int sku_id);

    List<T_MALL_SKU> getListSku(int spu_id);
}
