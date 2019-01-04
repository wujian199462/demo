package com.example.demo.util.security.symmetry;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PBE {
    private static String src = "imooc";
    public static void main(String[]args){
        jdkPEB();
    }
    public static void jdkPEB(){
        try {
            //初始化盐
            SecureRandom random = new SecureRandom();
            byte[]salt = random.generateSeed(8);

            //口令与密钥
            String password = "imooc";
            PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBEWITHMD5andDes");
            Key key = factory.generateSecret(pbeKeySpec);

            //加密
            PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt,100);
            Cipher cipher = Cipher.getInstance("PBEWITHMD5andDes");
            cipher.init(Cipher.ENCRYPT_MODE,key,pbeParameterSpec);
            byte [] resulet = cipher.doFinal(src.getBytes());
            System.out.println("jdk pbe encrypt:"+ Base64.encodeBase64String(resulet));

            //解密
            cipher.init(Cipher.DECRYPT_MODE,key,pbeParameterSpec);
            resulet = cipher.doFinal(resulet);
            System.out.println("jdk pbe dncrypt:"+new String(resulet));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
