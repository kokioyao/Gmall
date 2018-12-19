package service;

import cn.footman.bean.T_MALL_PRODUCT;
import cn.footman.mapper.SpuMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;

/**
 * @author footman77
 * @create 2018-12-05 14:57
 */
public class TestService {
//    @Autowired
//    private SpuMapper spuMapper;

    @Test
    public void insertTest() throws Exception{
//        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
//        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
//        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(in);
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//
//        SpuMapper mapper = sqlSession.getMapper(SpuMapper.class);
//        T_MALL_PRODUCT spu = new T_MALL_PRODUCT();
//
//        spu.setFlbh1(11);
//
//        mapper.insertTest(spu);
//
//        sqlSession.commit();
    }
}
