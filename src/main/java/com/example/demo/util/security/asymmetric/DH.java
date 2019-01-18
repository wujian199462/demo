package com.example.demo.util.security.asymmetric;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import java.util.Objects;

public class DH {
    private static String src = "imooc";

    public static void main(String[] args) {
        jdkDH();
    }

    public static void jdkDH() {
        try {
            //1.初始化发送方密钥
            KeyPairGenerator senderKeyPairGenerator = KeyPairGenerator.getInstance("DH");
            senderKeyPairGenerator.initialize(512);
            KeyPair senderKeyPair = senderKeyPairGenerator.generateKeyPair();
            byte[] senderPublicKeyEnc = senderKeyPair.getPublic().getEncoded();//发送方公钥 发送给接收方（网络，文件）

            //2.初始化接受方的密钥
            KeyFactory receiverKeyFactory = KeyFactory.getInstance("DH");
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(senderPublicKeyEnc);
            PublicKey reveiverPublicKey = receiverKeyFactory.generatePublic(x509EncodedKeySpec);//接收方的key
            DHParameterSpec dhParameterSpec = ((DHPublicKey) reveiverPublicKey).getParams();
            KeyPairGenerator receiverKeyPariGenerator = KeyPairGenerator.getInstance("DH");
            receiverKeyPariGenerator.initialize(dhParameterSpec);
            KeyPair receiverKeypair = receiverKeyPariGenerator.generateKeyPair();
            PrivateKey receiverPrivateKey = receiverKeypair.getPrivate();
            byte[] receiverPublicKeyEnc = receiverKeypair.getPublic().getEncoded();

            //3.密钥构建
            KeyAgreement receiverKeyAgreement = KeyAgreement.getInstance("DH");
            receiverKeyAgreement.init(receiverPrivateKey);
            receiverKeyAgreement.doPhase(reveiverPublicKey, true);
            SecretKey receiverDesKey = receiverKeyAgreement.generateSecret("DES");

            KeyFactory senderKeyFactory = KeyFactory.getInstance("DH");
            x509EncodedKeySpec = new X509EncodedKeySpec(receiverPublicKeyEnc);
            PublicKey senderPublicKey = senderKeyFactory.generatePublic(x509EncodedKeySpec);
            KeyAgreement senderKeyAgreement = KeyAgreement.getInstance("DH");
            senderKeyAgreement.init(senderKeyPair.getPrivate());
            senderKeyAgreement.doPhase(senderPublicKey, true);

            //发送方的本地密钥
            SecretKey senderDesKey = senderKeyAgreement.generateSecret("DES");
            if (Objects.equals(receiverDesKey, senderDesKey)) {
                System.out.println("双方密钥相同");
            }

            //4.加密
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, senderDesKey);
            byte[] result = cipher.doFinal(src.getBytes());
            System.out.println("jdk dh eccrypt:" + Base64.encodeBase64String(result));

            //解码
            cipher.init(Cipher.DECRYPT_MODE, receiverDesKey);
            result = cipher.doFinal(result);
            System.out.println("jdk dh eccrypt:" + new String(result));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
