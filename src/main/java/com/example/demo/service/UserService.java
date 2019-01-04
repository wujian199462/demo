package com.example.demo.service;

import com.example.demo.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    User selectUserByName(String name);

    List<User> selectAll();

    User selectUserByUserNameAndPassword(User user);

    int register(User user);

}
