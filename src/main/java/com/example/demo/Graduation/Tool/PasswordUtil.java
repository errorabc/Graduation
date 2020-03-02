package com.example.demo.Graduation.Tool;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class PasswordUtil {

    public  static String PasswordConfidential(String username,String password) throws Exception {

        if (password == null || password == "") {

        } else {
            String hashAlgorithName = "MD5";
            int hashIterations2 = 5;//加密次数
            ByteSource credentialsSalt = ByteSource.Util.bytes(username + "Yiqiwan");
            Object obj = new SimpleHash(hashAlgorithName, password, credentialsSalt, hashIterations2);
            return obj.toString();
        }
        return "";
    }

}
