package com.example.demo.service.Impl;

import com.example.demo.dao.ChatDao;
import com.example.demo.dao.EmployeeMapper;
import com.example.demo.domain.Employee;
import com.example.demo.domain.User;
import com.example.demo.service.ChatService;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public List<Employee> selectAll() {

        return employeeMapper.selectAll();
    }

    @Override
    public List<Employee> selectByIds(List<Employee> ids) {
        return null;
    }

}
