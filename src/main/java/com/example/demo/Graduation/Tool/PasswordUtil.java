package com.example.demo.Graduation.Tool;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PasswordUtil {
    @Value("${md5salt}")
    private String md5salt;

    public   String PasswordConfidential(String username, String password) throws Exception {
        if (password == null || password == "") {

        } else {
            String hashAlgorithName = "MD5";
            int hashIterations2 = 5;//加密次数
            ByteSource credentialsSalt = ByteSource.Util.bytes(username + md5salt);
            Object obj = new SimpleHash(hashAlgorithName, password, credentialsSalt, hashIterations2);
            return obj.toString();
        }
        return "";
    }

}
