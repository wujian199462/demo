package com.example.demo.service.Impl;


import com.example.demo.dao.DicDao;
import com.example.demo.dao.UserDao;
import com.example.demo.domain.Dic;
import com.example.demo.service.DicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DicServiceImpl implements DicService {

    @Autowired
    private DicDao dicDao;
    @Autowired
    private UserDao userDao;

    @Override
    public List<Dic> selectByTableName(String enTableName) {
        return dicDao.selectByTableName(enTableName);
    }
}
