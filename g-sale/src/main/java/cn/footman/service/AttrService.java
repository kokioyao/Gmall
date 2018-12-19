package cn.footman.service;

import cn.footman.bean.OBJECT_T_MALL_ATTR;

import java.util.List;

/**
 * @author footman77
 * @create 2018-12-05 23:21
 */
public interface AttrService {
    void save_attr(int flbh2, List<OBJECT_T_MALL_ATTR> list_attr);

    List<OBJECT_T_MALL_ATTR> get_attr_list(int flbh2);
}
