package com.example.demo;


import com.example.demo.domain.User;
import com.example.demo.util.security.RSA.RSA;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.example.demo.util.security.RSA.RSA.RSADecode;
import static com.example.demo.util.security.RSA.RSA.RSAEcode;

public class TestMain
{
    public static  void main(String[] args) throws Exception {
      byte[] sMsg =  RSAEcode("你在干嘛");
      System.out.println("密文："+Base64.encodeBase64String(sMsg));
      byte[] mMsg = RSADecode(sMsg,RSA.getPUBLICKEY());
      System.out.println("明文："+new String(mMsg));
    }

    public static String operDate(String str,int day){
        Date date;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(str);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_MONTH,day);
            return format.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }



    public static void HttpClient() throws IOException {
        User user = null;
        HttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet("http://192.168.223.81:9000/restFul/test");
        request.setHeader("Accept", "application/json");
        HttpResponse response = client.execute(request);
        HttpEntity entity = response.getEntity();
        ObjectMapper mapper = new ObjectMapper();
        user = mapper.readValue(entity.getContent(), User.class);
    }



    public static void getSt(String str){
        String[] first =str.split("String");
        String second = null;
        for (int i =0;i<first.length;i++){
            second+= first[i].split("private")[0];
        }
        String[] third =second.split(";");
        for(int i=0;i<third.length;i++){
            System.out.print(third[i].trim()+",");
        }

    }

    public  static int getWeek(){
        int wkeen []={7,1,2,3,4,5,6};
        Date today = new Date();
        Calendar c= Calendar.getInstance();
        c.setTime(today);
        int weekday=c.get(Calendar.DAY_OF_WEEK);
        return wkeen[weekday-1];
    }

}
