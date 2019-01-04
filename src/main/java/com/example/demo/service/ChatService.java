package com.example.demo.service;

import com.example.demo.domain.User;

import java.util.List;

public interface ChatService {

    /**
     * 获取所有的聊天用户
     * @return
     */
    List<User> getChatUser(String userName);
}
