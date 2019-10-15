package com.ygt.util;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
@Service
public class MD5Util {

    public String md5(String password) throws NoSuchAlgorithmException {
        MessageDigest instance = MessageDigest.getInstance("MD5");
        System.out.println(password);
        byte[] bs = instance.digest((password).getBytes());
        StringBuffer sb = new StringBuffer();
        for (byte b : bs) {
            String value = Integer.toHexString(b&0xff);
            if(value.length()<2) {
                value="0"+value;
            }
            sb.append(value);
        }
       return sb.toString();

    }
}
