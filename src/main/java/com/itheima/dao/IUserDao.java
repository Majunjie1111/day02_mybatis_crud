package com.itheima.dao;

import com.itheima.domain.QueryVo;
import com.itheima.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * User的接口
 */
public interface IUserDao {
    /**
     * 查询所有
     *
     * @return
     */
    @Select("select * from user")
    List<User> findAll();

    /**
     * 根据Id查找用户
     *
     * @return
     */
    @Select("select * from user where id =#{id}")
    User findById(int id);

    /**
     * 模糊查询
     *
     * @param name
     * @return
     */
    @Select("select * from user where username like #{username}")
    List<User> findByName(String name);

    /**
     * 保存用户
     */
    @Insert("insert into user(username,birthday,sex,address) values(#{username},#{birthday},#{sex},#{address})")
    void saveUser(User user);

    /**
     * 根据id删除用户
     *
     * @param id
     */
    @Delete("delete from user where id = #{id}")
    void deleteById(Integer id);

    /**
     * 更新用户信息
     *
     * @param user
     */
    @Update("update user set username=#{username},birthday=#{birthday},sex=#{sex},address=#{address} where id = #{id}")
    void updateUser(User user);

    /**
     * 查询总记录数
     *
     * @return
     */
    @Select("select count(id) from user")
    int findTotal();


    /**
     * 按条件查询
     *
     * @param
     * @return
     */
    @Select("select * from user where username like #{user.username} and sex = #{user.sex}")
    List<User> findByCondition(QueryVo vo);


}
