package cn.footman.mapper;

import cn.footman.bean.DETAIL_T_MALL_SKU;
import cn.footman.bean.T_MALL_SKU;

import java.util.List;
import java.util.Map;

/**
 * @author footman77
 * @create 2018-12-12 19:52
 */
public interface ItemMapper {


    DETAIL_T_MALL_SKU selectSkuDetail(Map<Object,Object> map);

    List<T_MALL_SKU> selectSkuListBySpu(int spu_id);
}
