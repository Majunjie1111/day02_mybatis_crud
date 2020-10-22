package com.itheima;

import com.itheima.dao.IUserDao;
import com.itheima.domain.QueryVo;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class mybatisTest {
    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;

    /**
     * 初始化数据
     *
     * @throws IOException
     */
    @Before
    public void init() throws IOException {
        //1.读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建工厂SqlSessionFactory
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3.创建sqlSession对象
        sqlSession = factory.openSession();
        //4.创建动态代理对象
        userDao = sqlSession.getMapper(IUserDao.class);
    }

    /**
     * 释放资源
     *
     * @throws IOException
     */
    @After
    public void destory() throws IOException {
        sqlSession.commit();
        sqlSession.close();
        in.close();
    }

    /**
     * 根据Id查找用户
     */
    @Test
    public void findById() {
        User user = userDao.findById(41);
        System.out.println(user);
    }


    /**
     * 查询所有用户
     */
    @Test
    public void findAll() {
        List<User> users = userDao.findAll();
        for (User user :
                users) {
            System.out.println(user);
        }
    }

    /**
     * 模糊查询
     */
    @Test
    public void findByName() {
        List<User> users = userDao.findByName("%王%");
        for (User user :
                users) {
            System.out.println(user);
        }
    }

    /**
     * 保存用户
     */
    @Test
    public void saveUser() {
        User u = new User();
        u.setUsername("注解测试名字");
        u.setSex("男");
        u.setAddress("佛山");
        u.setBirthday(new Date());
        userDao.saveUser(u);
    }


    /**
     * 根据id删除用户
     */
    @Test
    public void deleteById() {
        userDao.deleteById(53);
    }

    /**
     * 更新用户
     */
    @Test
    public void updateUser() {
        User u = new User();
        u.setId(57);
        u.setUsername("注解测试名字");
        u.setSex("女");
        u.setAddress("佛山");
        u.setBirthday(new Date());
        userDao.updateUser(u);
    }

    /**
     * 查询总数
     */
    @Test
    public void findTotal() {
        int total = userDao.findTotal();
        System.out.println(total);
    }


    /**
     * 根据条件查询
     */
    @Test
    public void findByCondition() {
        QueryVo vo = new QueryVo();
        User u = new User();
        u.setUsername("%王%");
        u.setSex("女");
        vo.setUser(u);
        List<User> users = userDao.findByCondition(vo);
        for (User user :
                users) {
            System.out.println(user);
        }
    }
}