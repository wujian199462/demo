package com.example.demo.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class Sheduled {

    static int i = 0;
    // @Scheduled(cron = "*/5 * * * * *")
    /*
    public static void a(){
        int count = 0;
        if(i==0){
            System.out.println("000");
            if(count>5){
                i=1;
            }
            count++;

        }else {
            System.out.println("111");
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                    i = 1;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
*/


}
