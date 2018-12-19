package cn.footman.bean;

import java.util.List;

/**
 * @author footman77
 * @create 2018-12-16 15:56
 */
public class OBJECT_T_MALL_FLOW extends T_MALL_FLOW {

    private List<T_MALL_ORDER_INFO> list_info;

    public List<T_MALL_ORDER_INFO> getList_info() {
        return list_info;
    }

    public void setList_info(List<T_MALL_ORDER_INFO> list_info) {
        this.list_info = list_info;
    }
}
