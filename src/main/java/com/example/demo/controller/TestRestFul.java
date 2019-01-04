package com.example.demo.controller;

import com.example.demo.util.security.RSA.RSA;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "/restFul",method = RequestMethod.GET)
public class TestRestFul {


    @RequestMapping("test")
    public JSONObject test(String a){
        Map<String,Object> map = new HashMap<String,Object>();
        byte[] msg = RSA.RSAEcode("岑永威你是猪 吗?");
        map.put("rsaPublicKey", RSA.getPUBLICKEY());
        map.put("msg", msg);
        JSONObject jsonString = JSONObject.fromObject(map);
        return jsonString;
    }


}
