package com.example.demo.Graduation.Tool;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

//日期工具类
public class DateTime {

    public static Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

}
