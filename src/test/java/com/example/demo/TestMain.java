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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.example.demo.util.security.RSA.RSA.RSADecode;
import static com.example.demo.util.security.RSA.RSA.RSAEcode;

public class TestMain {
    public static void main(String[] args) throws Exception {


       /* List l = isWeek("2019-01-01","2019-01-31",7);
        System.out.println(l);*/

        //  List l= findDates("2019-01-01","2019-01-01");
        String[] holidayArry = {"2019-01-01", "2019-01-05", "2019-01-06", "2019-01-12", "2019-01-13", "2019-01-16"};

        String wc = "2019-01-16";
        User user = new User();
        func(holidayArry, 4, wc, user);

    }

    public static void func(String[] holidayArry, int t, String now, User user) {
        int alldays = 0;

        for (int i = 0; i < t; i++) {
            if (i == 0) {
                now = operDate1(now, 1);
            }
            now = operDate1(now, -1);
            if (Arrays.asList(holidayArry).contains(now)) {
                alldays++;
            }
            user.setPassword(operDate1(now, -1));
        }

        if (alldays != 0) {
            now = operDate1(now, -1);
            func(holidayArry, alldays, now, user);
        }
    }

    public static String operDate1(String str, int day) {
        Date date;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(str);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_MONTH, day);
            return format.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }


    public static List<Date> findDates(String startDate, String endDate) throws ParseException {
        SimpleDateFormat sdfd = new SimpleDateFormat("yyy-MM-dd");
        Date dStart = sdfd.parse(startDate);
        Date dEnd = sdfd.parse(endDate);

        Calendar cStart = Calendar.getInstance();
        cStart.setTime(dStart);

        List dateList = new ArrayList();
        //别忘了，把起始日期加上
        dateList.add(dStart);
        // 此日期是否在指定日期之后
        while (dEnd.after(cStart.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            cStart.add(Calendar.DAY_OF_MONTH, 1);
            dateList.add(cStart.getTime());
        }
        return dateList;
    }


    public static List isWeek(String start, String end, int week) throws ParseException {
        SimpleDateFormat sdfd = new SimpleDateFormat("yyy-MM-dd");
        Date startDate = sdfd.parse(start);
        Date endDate = sdfd.parse(end);
        int w[] = {0, 2, 3, 4, 5, 6, 7, 1};
        Calendar cal = Calendar.getInstance();
        List list = new ArrayList();
        Date date = startDate;
        while (!date.equals(endDate)) {
            //  System.out.println(date);
            cal.setTime(date);
            int weekday = cal.get(Calendar.DAY_OF_WEEK);//
            if (w[week] == weekday) {
                list.add(date);
            }
            cal.add(Calendar.DATE, 1);
            date = cal.getTime();
        }
        return list;
    }


    public static String operDate(String str, int day) {
        Date date;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(str);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_MONTH, day);
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


    public static void getSt(String str) {
        String[] first = str.split("String");
        String second = null;
        for (int i = 0; i < first.length; i++) {
            second += first[i].split("private")[0];
        }
        String[] third = second.split(";");
        for (int i = 0; i < third.length; i++) {
            System.out.print(third[i].trim() + ",");
        }

    }

    public static int getWeek() {
        int wkeen[] = {7, 1, 2, 3, 4, 5, 6};
        Date today = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        int weekday = c.get(Calendar.DAY_OF_WEEK);
        return wkeen[weekday - 1];
    }

}
