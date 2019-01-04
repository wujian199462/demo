package com.example.demo.service;

import com.example.demo.domain.Dic;

import java.util.List;

public interface DicService {
    List<Dic> selectByTableName(String enTableName);
}
