package com.example.demo.controller;


import com.example.demo.domain.User;
import com.example.demo.domain.utilModel.MessageResult;
import com.example.demo.service.ChatService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ChatController {
    @Autowired
    private ChatService chatService;


    @ResponseBody
    @RequestMapping(value = "/getChatUser")
    public MessageResult getChatUser(HttpSession session) {
        MessageResult messageResult = new MessageResult();
        String userName = (String) session.getAttribute("username");
        List<User> list = chatService.getChatUser(userName);
        messageResult.setData(list);
        return messageResult;
    }

}
