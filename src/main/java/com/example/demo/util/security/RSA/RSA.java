package com.example.demo.util.security.RSA;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public  class RSA {

    private static final RSAPublicKey PUBLICKEY ;
    private static final RSAPrivateKey PRIVATEKEY ;

    public static RSAPublicKey getPUBLICKEY() {
        return PUBLICKEY;
    }

    public static RSAPrivateKey getPRIVATEKEY() {
        return PRIVATEKEY;
    }

    //生成公钥和私钥对
     static {
        KeyPairGenerator keyPairGenerator = null;
        RSAPublicKey rsaPublicKey = null;
        RSAPrivateKey rsaPrivateKey =null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(512);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
            rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
            System.out.println("生成公钥："+Base64.encodeBase64String(rsaPublicKey.getEncoded()));
            System.out.println("生成私钥："+Base64.encodeBase64String(rsaPrivateKey.getEncoded()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        PUBLICKEY = rsaPublicKey;
        PRIVATEKEY = rsaPrivateKey;
    }

    //私钥加密
    public static byte[] RSAEcode(String msg){
        RSAPrivateKey  rsaPrivateKey = PRIVATEKEY;
        System.out.println("使用私钥："+Base64.encodeBase64String(rsaPrivateKey.getEncoded()));
        byte[] result = null;
        try{
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE,privateKey);
            result = cipher.doFinal(msg.getBytes());
            System.out.println("私钥加密密文:"+Base64.encodeBase64String(result));
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    //公钥解密
    public static byte[] RSADecode(byte[] msg,RSAPublicKey rsaPublicKey){
        //RSAPublicKey rsaPublicKey = PUBLICKEY;
        System.out.println("使用公钥："+Base64.encodeBase64String(rsaPublicKey.getEncoded()));
        byte[]result = null;
        try{
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE,publicKey);
            result=  cipher.doFinal(msg);
            System.out.println("公钥解密明文:"+new String(result));
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
