package cn.footman.service;

import cn.footman.bean.MODEL_T_MALL_SKU_ATTR_VALUE;
import cn.footman.bean.OBJECT_T_MALL_SKU;
import cn.footman.bean.T_MALL_SKU_ATTR_VALUE;

import java.util.List;

/**
 * @author footman77
 * @create 2018-12-12 0:01
 */
public interface ListService {
    List<OBJECT_T_MALL_SKU> getListByFlbh2(int flbh2);

    List<OBJECT_T_MALL_SKU> getListByAttr(List<T_MALL_SKU_ATTR_VALUE> list_attr, int flbh2);
}
