package cn.footman.bean;

import java.util.List;

/**
 * @author footman77
 * @create 2018-12-05 20:18
 */
public class OBJECT_T_MALL_ATTR extends T_MALL_ATTR {
    private List<T_MALL_VALUE> list_value;

    public List<T_MALL_VALUE> getList_value() {
        return list_value;
    }

    public void setList_value(List<T_MALL_VALUE> list_value) {
        this.list_value = list_value;
    }
}
