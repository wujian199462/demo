package com.example.demo.util.security.symmetry;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class AES {
    private static String src="jerry";
    public static void main(String [] args){
        jdkAES();
    }
    public static void jdkAES(){
        try {
            //生成KEY
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");//采用的是哪种算法
            keyGenerator.init(128);//设置长度
            SecretKey secretKey = keyGenerator.generateKey();//生成KEY
            byte[] keyBytes = secretKey.getEncoded();

            //key转换
            Key key = new SecretKeySpec(keyBytes,"AES");

            //加密
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE,key);
            byte[] resule = cipher.doFinal(src.getBytes());
            System.out.println("jdk aes encrypt:"+ Base64.encodeBase64String(resule));

            //界面
            cipher.init(Cipher.DECRYPT_MODE,key);
            resule = cipher.doFinal(resule);
            System.out.println("jdk aes descrypt:"+new String(resule));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
