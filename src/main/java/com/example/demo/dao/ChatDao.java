package com.example.demo.dao;


import com.example.demo.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ChatDao {
    /**
     * 获取所有的聊天用户
     *
     * @return
     */
    List<User> getChatUser(String userName);
}
