package com.dao;

import com.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

    public interface userDao {
        /**
         * 查询所有
         * @return
         */
//        @Select("select * from account")
        List<User> findAll();

        /**
         * 根据id查询
         * @param id
         * @return
         */
        User findById(Integer id);

        /**
         * 保存操作
         * @param user
         * @return 影响数据库记录的行数
         */
        int saveUser(User user);

        /**
         * 用户更新操作
         * @return 影响数据库记录的行数
         */
        int updateUser(User user);

        /**
         * 用户删除
         * @param id
         * @return
         */
        int deleteUser(Integer id);

        /**
         * 用户模糊查询
         * @param name
         * @return
         */
        List<User> findByName(String name);

        /**
         * 查询总记录条数
         * @return
         */
        int findTotal();

    }
