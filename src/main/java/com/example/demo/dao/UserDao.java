package com.example.demo.dao;


import com.example.demo.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface UserDao {
    /**
     * 根据用户名查询注册是否重复
     * @param userName
     * @return
     */
    User selectUserByName(String userName);

    List<User> selectAll();

    User selectUserByUserNameAndPassword(User user);

    /**
     * 组测用户
     * @param user
     * @return
     */
    int register(User user);
}
