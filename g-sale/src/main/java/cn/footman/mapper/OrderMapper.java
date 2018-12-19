package cn.footman.mapper;

import cn.footman.bean.OBJECT_T_MALL_FLOW;
import cn.footman.bean.OBJECT_T_MALL_ORDER;
import cn.footman.bean.T_MALL_ORDER_INFO;

import java.util.List;
import java.util.Map;

/**
 * @author footman77
 * @create 2018-12-16 23:32
 */
public interface OrderMapper {
    void insert_order(Map<Object,Object> map_order);


    void insert_flow(Map<Object, Object> map_flow);

    void insert_infos(Map<Object, Object> map_info);

    void update_order(OBJECT_T_MALL_ORDER order);

    void update_flow(OBJECT_T_MALL_FLOW object_T_MALL_FLOW);

    void delete_carts(Map<Object,Object> map_cart);

    long select_kc(int id);

    void update_kc(T_MALL_ORDER_INFO info);

    long select_count_kc(Map<Object,Object> map);

    long select_kc_without_lock(int sku_id);
}
