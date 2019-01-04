package com.example.demo.controller;




import com.example.demo.util.security.RSA.RSA;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import static com.example.demo.util.security.RSA.RSA.RSAEcode;

@RestController
@RequestMapping(value = "/restFul",method = RequestMethod.GET)
public class TestRestFul {


    @RequestMapping("test")
    public JSONObject test(String a){
        System.out.println("!1111");
        System.out.println(a);
        Map<String,Object> map = new HashMap<String,Object>();
        byte[] msg = RSA.RSAEcode("岑永威你是猪吗?");
        map.put("rsaPublicKey", RSA.getPUBLICKEY());
        map.put("msg", msg);
        JSONObject jsonString = JSONObject.fromObject(map);

        return jsonString;

    }




}
