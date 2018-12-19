package cn.footman.mapper;

import cn.footman.bean.T_MALL_PRODUCT;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author footman77
 * @create 2018-12-04 13:00
 */
public interface SpuMapper {

//    void insertTest(T_MALL_PRODUCT spu);

    void insert_spu(T_MALL_PRODUCT spu);

    void insert_images(Map<Object, Object> map);

    List<T_MALL_PRODUCT> select_spu_list(HashMap<Object,Object> map);
}
