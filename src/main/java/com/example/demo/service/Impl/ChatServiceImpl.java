package com.example.demo.service.Impl;

import com.example.demo.dao.ChatDao;
import com.example.demo.dao.UserDao;
import com.example.demo.domain.User;
import com.example.demo.service.ChatService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatDao chatDao;

    @Override
    public List<User> getChatUser(String userName) {

        return chatDao.getChatUser(userName);
    }

}
