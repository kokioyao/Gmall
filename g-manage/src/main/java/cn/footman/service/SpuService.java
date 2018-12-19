package cn.footman.service;

import cn.footman.bean.T_MALL_PRODUCT;

import java.util.List;

/**
 * @author footman77
 * @create 2018-12-05 11:02
 */

public interface SpuService {

    void save_spu(T_MALL_PRODUCT spu, List<String> list_image_name,int mainPic);

    List<T_MALL_PRODUCT> get_spu_list(int pp_id, int flbh2);
}
