package com.example.demo.dao;

import com.example.demo.domain.Dic;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DicDao {
    List<Dic> selectByTableName(String enTableName);
}
