package cn.footman.mapper;

import cn.footman.bean.OBJECT_T_MALL_ATTR;
import cn.footman.bean.T_MALL_VALUE;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//import com.atguigu.bean.OBJECT_T_MALL_ATTR;

public interface AttrMapper {

	 void insert_attr(@Param("flbh2") int flbh2, @Param("attr") OBJECT_T_MALL_ATTR attr);

	 void insert_values(@Param("attr_id") int attr_id, @Param("list_value") List<T_MALL_VALUE> list_value);

	 List<OBJECT_T_MALL_ATTR> select_attr_list(int flbh2);
}
