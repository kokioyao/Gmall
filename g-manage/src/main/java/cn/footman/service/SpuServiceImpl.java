package cn.footman.service;

import cn.footman.bean.T_MALL_PRODUCT;
import cn.footman.mapper.SpuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * @author footman77
 * @create 2018-12-05 11:04
 */
@Service
public class SpuServiceImpl implements SpuService{

    @Autowired
    private SpuMapper spuMapper;

    @Override
    public void save_spu(T_MALL_PRODUCT spu, List<String> list_image_name,int mainPic) {
        //插入spu信息，生成主键
        spu.setShp_tp(list_image_name.get(mainPic));
        spuMapper.insert_spu(spu);

        Map<Object, Object> map = new HashMap<>();
        map.put("shp_id",spu.getId());
        map.put("list_image_name",list_image_name);
        spuMapper.insert_images(map);


    }

    @Override
    public List<T_MALL_PRODUCT> get_spu_list(int pp_id, int flbh2) {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("pp_id",pp_id);
        map.put("flbh2",flbh2);
        List<T_MALL_PRODUCT> list = spuMapper.select_spu_list(map);
        return list;
    }
}
