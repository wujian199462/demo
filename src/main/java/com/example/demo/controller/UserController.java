package com.example.demo.controller;

import com.example.demo.domain.Dic;
import com.example.demo.domain.User;
import com.example.demo.domain.utilModel.MessageResult;
import com.example.demo.service.DicService;
import com.example.demo.service.UserService;
import com.example.demo.util.Excle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private DicService dicService;



    @ResponseBody
    @RequestMapping(value = "/login")
    public MessageResult login(@RequestBody User record,HttpSession session){
        MessageResult messageResult = new MessageResult();
        User user = userService.selectUserByUserNameAndPassword(record);
        if(user!=null){
            messageResult.setStatus(true);
            messageResult.setData(user);
            session.setAttribute("username",user.getUserName());
            session.setMaxInactiveInterval(30000);//以秒为单位
            String a = (String) session.getAttribute("username");
            System.out.println(a);
        }else{
            messageResult.setStatus(false);
        }
        return messageResult;
    }


    @ResponseBody
    @RequestMapping(value = "/register")
    public MessageResult register(@RequestBody User record){
        MessageResult messageResult = new MessageResult();
        //判断用户名是否存在
        if(userService.selectUserByName(record.getUserName())!=null){
            messageResult.setStatus(false);
            messageResult.setMessage("用户名已存在");
        }else {
            int i = userService.register(record);
            if (i != 0) {
                messageResult.setStatus(true);
                messageResult.setMessage("注册成功");
            } else {
                messageResult.setStatus(false);
                messageResult.setMessage("注册失败");
            }
        }
        return messageResult;
    }

    @ResponseBody
    @RequestMapping(value = "/download")
    public MessageResult download(@RequestBody String tableName) throws Exception {
        MessageResult messageResult = new MessageResult();

        List<User> list = userService.selectAll();
        List<Dic> listTitle =dicService.selectByTableName("user");    //查询字典值中的中文列名作为表头
        String [] titleName = new String[listTitle.size()];  //将查询到的表头放到String[] 数组中
        for(int i=0;i<listTitle.size();i++){
            titleName[i] = listTitle.get(i).getCnColName();
        }

       int status =  Excle.downloadAllClassmate(list,titleName,"信息表","D://Excel","信息表.xls");

        if(status==1){
            messageResult.setStatus(true);
            messageResult.setMessage("下载成功！");
        }else{
            messageResult.setStatus(false);
            messageResult.setMessage("下载失败！");
        }

        return messageResult;
    }


}
