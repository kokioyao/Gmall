package cn.footman.bean;

import java.util.List;

/**
 * @author footman77
 * @create 2018-12-16 16:04
 */
public class OBJECT_T_MALL_ORDER extends T_MALL_ORDER {

    private List<OBJECT_T_MALL_FLOW> list_flow;

    public List<OBJECT_T_MALL_FLOW> getList_flow() {
        return list_flow;
    }

    public void setList_flow(List<OBJECT_T_MALL_FLOW> list_flow) {
        this.list_flow = list_flow;
    }
}
