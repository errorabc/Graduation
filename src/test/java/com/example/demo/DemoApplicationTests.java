package com.example.demo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Graduation.Tool.DateTime;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String starttime = format.format(new Date());
        String endtime = format.format(new Date());
        System.out.println(starttime+" "+"00:00:00");
        System.out.println(endtime+" "+"23:59:59");

    }

}
