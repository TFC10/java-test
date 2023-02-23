package org.example.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.mapper.UserMapper;
import org.example.pojo.User;
import org.example.utils.SqlSessionUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisTest {
    @Test
    public void testInsert() throws IOException {

        //获取核心配置文件信息的输入流，才知道下一步要干什么
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        //获取获取SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        //获取SqlSessionFactory工厂对象
        SqlSessionFactory build = sqlSessionFactoryBuilder.build(is);
        //获取sql的会话对象SqlSession，不传递参数是（不会自动提交事务），是MyBaits提供操作数据库的对象，传递一个true会自动提交事务
        SqlSession sqlSession = build.openSession(true);
        //获取UserMapper的代理实现对象，这种方法使用最多
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //调用mapper接口中的方法，实现添加用户信息的功能
        //原理是通过mapper接口的全类名找到匹配namespace值的映射文件，然后通过mapper接口中的方法名找到映射文件中id相同的，然后找到设定的sql语句
        //int result = mapper.insertUser();
        // sqlSession里面还提供了直接执行sql语句的函数，只需要提供唯一标识找到sql语句，唯一标识是namsspace.sqlid，及sql
        // 语句方法所在的位置，例如下面的
        int insert = sqlSession.insert("org.example.mapper.UserMapper.insertUser");
        System.out.println("结果："+insert);
        //这时候数据库里面没有数据，说明还需要提交事务，因为默认操作是回滚的,还需要使用commit方法进行提交事务，也可以在
        //生成sqlSession传递参数设置是否自动提交事务
        sqlSession.commit();
        //关闭sqlSession对象
        sqlSession.close();
    }
    @Test
    //测试通过自己自定义一个获取sqlsession的工具类获取sql对象
    public void testUpdate() {
        SqlSession sqlSession1 = SqlSessionUtils.getSqlSession();
        UserMapper mapper = sqlSession1.getMapper(UserMapper.class);
        mapper.updateUser();
        sqlSession1.close();
    }
    @Test
    //测试通过自己自定义一个获取sqlsession的工具类获取sql对象
    public void testDelete() {
        SqlSession sqlSession1 = SqlSessionUtils.getSqlSession();
        UserMapper mapper = sqlSession1.getMapper(UserMapper.class);
        mapper.deleteUser();
        sqlSession1.close();
    }
    @Test
    //测试通过自己自定义一个获取sqlsession的工具类获取sql对象
    public void testGetUserId() {
        SqlSession sqlSession1 = SqlSessionUtils.getSqlSession();
        UserMapper mapper = sqlSession1.getMapper(UserMapper.class);
        User user = mapper.getUserId();
        System.out.println(user);
    }
    @Test
    //测试通过自己自定义一个获取sqlsession的工具类获取sql对象
    public void testGetAllUser() {
        SqlSession sqlSession1 = SqlSessionUtils.getSqlSession();
        UserMapper mapper = sqlSession1.getMapper(UserMapper.class);
        List<User> allUser = mapper.getAllUser();
       allUser.forEach(System.out::println);
    }
}
