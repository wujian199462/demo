package com.example.demo.service;

import com.example.demo.domain.Employee;
import com.example.demo.domain.User;

import java.util.List;

public interface EmployeeService {

    /**
     * 获取所有的聊天用户
     * @return
     */
    List<Employee> selectAll();

    List<Employee> selectByIds(List<Employee>ids);

}
