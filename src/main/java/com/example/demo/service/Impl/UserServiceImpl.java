package com.example.demo.service.Impl;

import com.example.demo.dao.UserDao;
import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User selectUserByName(String name) {
        return userDao.selectUserByName(name);
    }

    @Override
    public List<User> selectAll() {
        return userDao.selectAll();
    }

    @Override
    public User selectUserByUserNameAndPassword(User user) {
        BASE64Encoder base64Encoder = new BASE64Encoder();
        user.setPassword(base64Encoder.encode(user.getPassword().getBytes()));
        return userDao.selectUserByUserNameAndPassword(user);
    }

    @Override
    public int register(User user) {
        user.setId(java.util.UUID.randomUUID().toString());
        BASE64Encoder base64Encoder = new BASE64Encoder();
        user.setPassword(base64Encoder.encode(user.getPassword().getBytes()));//密码加密
        return userDao.register(user);
    }

}
