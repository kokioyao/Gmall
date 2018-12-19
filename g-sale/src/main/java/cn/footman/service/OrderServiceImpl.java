package cn.footman.service;

import cn.footman.bean.OBJECT_T_MALL_FLOW;
import cn.footman.bean.OBJECT_T_MALL_ORDER;
import cn.footman.bean.T_MALL_ADDRESS;
import cn.footman.bean.T_MALL_ORDER_INFO;
import cn.footman.exception.OverSaleException;
import cn.footman.mapper.OrderMapper;
import cn.footman.util.MyDateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author footman77
 * @create 2018-12-16 23:31
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void save_order(OBJECT_T_MALL_ORDER order, T_MALL_ADDRESS addr) {

        List<Integer> list_id = new ArrayList<>();

        Map<Object, Object> map_order = new HashMap<>();
        map_order.put("order",order);
        map_order.put("address",addr);
        orderMapper.insert_order(map_order);


        List<OBJECT_T_MALL_FLOW> list_flow = order.getList_flow();
        for(int i = 0; i < list_flow.size(); i++){
            //保存flow 物流
            OBJECT_T_MALL_FLOW flow = list_flow.get(i);
            Map<Object, Object> map_flow = new HashMap<>();
            map_flow.put("dd_id",order.getId());
            flow.setMdd(addr.getYh_dz());
            map_flow.put("flow",flow);
            orderMapper.insert_flow(map_flow);

            //保存订单info
            List<T_MALL_ORDER_INFO> list_info = flow.getList_info();
            Map<Object, Object> map_info = new HashMap<>();
            map_info.put("dd_id",order.getId());
            map_info.put("flow_id",flow.getId());
            map_info.put("list_info",list_info);
            orderMapper.insert_infos(map_info);


            for(int j = 0; j  < list_info.size(); j++){
                list_id.add(list_info.get(j).getGwch_id());
            }



        }


        //删除购物车中已经生成订单的对象
        Map<Object, Object> map_cart = new HashMap<>();
        map_cart.put("list_id",list_id);
        orderMapper.delete_carts(map_cart);

    }

    @Override
    public void pay_success(OBJECT_T_MALL_ORDER order) throws OverSaleException {
        //修改订单状态，
        order.setJdh(2);
        orderMapper.update_order(order);


        //修改物流信息
        List<OBJECT_T_MALL_FLOW> list_flow = order.getList_flow();
        for(int i = 0; i < list_flow.size();i++){
            OBJECT_T_MALL_FLOW flow = list_flow.get(i);
            //物流信息逐条更新
            flow.setPsmsh("商品正在出库");
            flow.setYwy("老张");
            flow.setPsshj(MyDateUtil.getMyDate(1));
            flow.setLxfsh("12314131242");
            orderMapper.update_flow(flow);


            List<T_MALL_ORDER_INFO> list_info = flow.getList_info();
            //更新sku表
            for(int j = 0; j < list_info.size();j++){
                T_MALL_ORDER_INFO info = list_info.get(i);

                long kc = 0;
                Map<Object, Object> map = new HashMap<>();
                map.put("count",10);
                map.put("id",info.getSku_id());
                long count = orderMapper.select_count_kc(map);
                //如果sku表中，sku_id的商品的库存数量少于10
                if(count == 0){
                    kc = orderMapper.select_kc(info.getSku_id());//查库存数量
                }else {
                    kc = orderMapper.select_kc_without_lock(info.getSku_id());
                }
                //如果库存的数量大于所需要购买的数量，修改sku销量 数量
                if(kc >= info.getSku_shl()){
                    orderMapper.update_kc(info);

                }else {
                    throw new OverSaleException("Don't have enough kc");
                }
            }
        }


        //修改sku数量销量

        //修改订单状态出库
        order.setYjsdshj(MyDateUtil.getMyDate(3));
//        order.setJdh(3);
        orderMapper.update_order(order);
    }
}
