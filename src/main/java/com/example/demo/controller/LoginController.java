package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    UserService userService;

    @RequestMapping("/loginUI")
    public String index() {
        return "loginUI";
    }

    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public User login(@RequestBody User record) {
        record.setPassword("jerry");
        record.setPassword("12");
        User user = userService.selectUserByUserNameAndPassword(record);
        System.out.println(user);
        return user;
    }

}
