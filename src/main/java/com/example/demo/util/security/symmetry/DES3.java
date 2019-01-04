package com.example.demo.util.security.symmetry;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import java.security.Key;
import java.security.SecureRandom;

public class DES3 {
    private static String input ="jerry";
    public static void main(String[] args){
        jdk3DES();
    }
    public static void jdk3DES(){
        try {
            //生成KEY
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
           // keyGenerator.init(168);
            keyGenerator.init(new SecureRandom()); //这种方法是设置默认长度
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] bytesKey = secretKey.getEncoded();

            //KEY转换
            DESedeKeySpec desKeySpec = new DESedeKeySpec(bytesKey);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
            Key convertSecretKey =factory.generateSecret(desKeySpec);

            //加密
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE,convertSecretKey);
            byte[] result = cipher.doFinal(input.getBytes());
            System.out.println("jdk des encrype:"+ Hex.encodeHexString(result));

            //解密
            cipher.init(Cipher.DECRYPT_MODE,convertSecretKey);
            result = cipher.doFinal(result);
            System.out.println("jdk des dencrype:"+new String(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
