package com.example.demo;

import com.example.demo.Graduation.Tool.DateTime;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String hashAlgorithName = "MD5";
        String password = "123456";
        int hashIterations2 = 5;//加密次数
        String username="user123";
        ByteSource credentialsSalt = ByteSource.Util.bytes(username + "Yiqiwan");
        Object obj = new SimpleHash(hashAlgorithName, password, credentialsSalt, hashIterations2);
       // System.out.println("user " + obj);

        //String uuid = UUID.randomUUID().toString();
        //System.out.println(uuid);
        System.out.println(DateTime.strToDateLong(df.format(new Date())));

        System.out.println();

    }

}
