package cn.footman.service;

import cn.footman.bean.DETAIL_T_MALL_SKU;
import cn.footman.bean.T_MALL_SKU;
import cn.footman.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author footman77
 * @create 2018-12-12 19:46
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public DETAIL_T_MALL_SKU getSkuDetail(int sku_id) {
        Map<Object, Object> map = new HashMap<>();
        map.put("sku_id",sku_id);
        return itemMapper.selectSkuDetail(map);
    }

    @Override
    public List<T_MALL_SKU> getListSku(int spu_id) {
        return itemMapper.selectSkuListBySpu(spu_id);
    }
}
