package cn.footman.util;

import cn.footman.bean.OBJECT_T_MALL_SKU;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author footman77
 * @create 2018-12-19 9:40
 */
public class MyCacheUtil{


    public static String interKeys(String... keys){
        Jedis jedis = null;
        try {
            jedis = JedisPoolUtils.getJedis();
        } catch (Exception e) {
            e.printStackTrace();
        }
        jedis.zinterstore("k0",keys);
        return "k0";
    }


    public static <T> List<T> getList(String key, Class<T> t){
        List<T> list = new ArrayList<T>();

        Jedis jedis = null;
        try {
            jedis = JedisPoolUtils.getJedis();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        Set<String> zrange = jedis.zrange(key, 0, -1);
        Iterator<String> iterator = zrange.iterator();
        while (iterator.hasNext()){
            list.add(MyJsonUtil.json_to_object(iterator.next(),t));
        }
        return list;
    }

    //将list中的内容添加到redis中
    public static <T> void addList(String key, List<T> t) {

        Jedis jedis = null;
        try {
            jedis = JedisPoolUtils.getJedis();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(int i = 0 ; i < t.size(); i++){

            jedis.del(key);
            jedis.zadd(key,i,MyJsonUtil.object_to_json(t.get(i)));
        }
    }
}
