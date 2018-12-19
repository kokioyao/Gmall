package cn.footman.service;

import cn.footman.bean.MODEL_T_MALL_SKU_ATTR_VALUE;
import cn.footman.bean.OBJECT_T_MALL_SKU;
import cn.footman.bean.T_MALL_SKU_ATTR_VALUE;
import cn.footman.mapper.ListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author footman77
 * @create 2018-12-12 0:01
 */
@Service
public class ListServiceImpl implements ListService {

    @Autowired
    private ListMapper listMapper;
    @Override
    public List<OBJECT_T_MALL_SKU> getListByFlbh2(int flbh2) {
        return listMapper.selectListByFlbh2(flbh2);
    }

    @Override
    public List<OBJECT_T_MALL_SKU> getListByAttr(List<T_MALL_SKU_ATTR_VALUE> list_attr, int flbh2) {

        StringBuilder subSql = new StringBuilder("");
        //根据属性集合动态拼接条件过滤
        subSql.append(" and sku.id in (select sku0.sku_id from ");
        if(list_attr != null && list_attr.size() > 0){
            for(int i = 0; i < list_attr.size(); i++){
                subSql.append(" (select sku_id from t_mall_sku_attr_value WHERE shxm_id = "+list_attr.get(i).getShxm_id()+" and shxzh_id = "+list_attr.get(i).getShxzh_id()+") sku"+i+" ");
                if(i < list_attr.size() - 1 && list_attr.size() > 1){
                    subSql.append(" , ");
                }
            }
            if(list_attr.size() > 1){
                subSql.append(" where ");

                for(int i = 0; i < list_attr.size(); i++){
                    if(i < list_attr.size() - 1){
                        subSql.append("sku"+i+".sku_id=sku"+(i+1)+".sku_id");

                        if(list_attr.size() > 2 && i < list_attr.size() - 2){
                            subSql.append(" and ");
                        }

                    }

                }
            }

        }


        subSql.append(" ) ");

        Map<Object, Object> map = new HashMap<>();
        map.put("subSql",subSql.toString());
        map.put("flbh2",flbh2);
        return listMapper.selectListByAttr(map);
    }
}
