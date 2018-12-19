package cn.footman.service;

import cn.footman.bean.OBJECT_T_MALL_ATTR;
import cn.footman.mapper.AttrMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author footman77
 * @create 2018-12-05 23:22
 */
@Service
public class AttrServiceImpl implements AttrService {

    @Autowired
    private AttrMapper attrMapper;

    @Override
    public void save_attr(int flbh2, List<OBJECT_T_MALL_ATTR> list_attr) {

        for(int i = 0; i < list_attr.size();i++){
            OBJECT_T_MALL_ATTR attr = list_attr.get(i);
            attrMapper.insert_attr(flbh2,attr);

            int attr_id = attr.getId();
            attrMapper.insert_values(attr_id,attr.getList_value());

        }
    }

    @Override
    public List<OBJECT_T_MALL_ATTR> get_attr_list(int flbh2) {
        List<OBJECT_T_MALL_ATTR> attr_list = attrMapper.select_attr_list(flbh2);
        return attr_list;

    }
}
