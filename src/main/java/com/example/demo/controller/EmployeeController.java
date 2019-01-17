package com.example.demo.controller;


import com.example.demo.domain.Employee;
import com.example.demo.domain.User;
import com.example.demo.domain.utilModel.MessageResult;
import com.example.demo.service.ChatService;
import com.example.demo.service.EmployeeService;
import com.example.demo.vo.Emp;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;


    @ResponseBody
    @RequestMapping(value = "/selectAll")
    public MessageResult selectAll(HttpSession session){
        MessageResult messageResult = new MessageResult();
        List<Employee> list = employeeService.selectAll();
        messageResult.setData(list);
        return messageResult;
    }

    @ResponseBody
    @RequestMapping(value = "/download")
    public MessageResult download(@RequestBody String[] ids){
        MessageResult messageResult = new MessageResult();
        List list = java.util.Arrays.asList(ids);
       List<Employee> employeeList= employeeService.selectByIds(list);
        return messageResult;
    }

}
