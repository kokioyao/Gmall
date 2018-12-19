package cn.footman.mapper;

import cn.footman.bean.OBJECT_T_MALL_SKU;

import java.util.List;
import java.util.Map;

/**
 * @author footman77
 * @create 2018-12-12 10:35
 */
public interface ListMapper {

    List<OBJECT_T_MALL_SKU> selectListByFlbh2(int flbh2);

    List<OBJECT_T_MALL_SKU> selectListByAttr(Map<Object,Object> map);
}
