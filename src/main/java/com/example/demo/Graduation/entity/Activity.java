package com.example.demo.Graduation.entity;

import java.math.BigDecimal;
import java.util.Date;

//活动管理
public class Activity {
    private String id;
    private String activity_name;//活动名称
    private Date activity_starttime;//活动开始时间
    private Date activity_endtime;//活动结束时间
    private String activity_content;//活动内容
    private float activity_discount;//活动折扣
    private int activity_status;//活动状态
    private String starttime;//
    private String endtime;

    public float getActivity_discount() {
        return activity_discount;
    }

    public void setActivity_discount(float activity_discount) {
        this.activity_discount = activity_discount;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActivity_name() {
        return activity_name;
    }

    public void setActivity_name(String activity_name) {
        this.activity_name = activity_name;
    }

    public Date getActivity_starttime() {
        return activity_starttime;
    }

    public void setActivity_starttime(Date activity_starttime) {
        this.activity_starttime = activity_starttime;
    }

    public Date getActivity_endtime() {
        return activity_endtime;
    }

    public void setActivity_endtime(Date activity_endtime) {
        this.activity_endtime = activity_endtime;
    }

    public String getActivity_content() {
        return activity_content;
    }

    public void setActivity_content(String activity_content) {
        this.activity_content = activity_content;
    }


    public int getActivity_status() {
        return activity_status;
    }

    public void setActivity_status(int activity_status) {
        this.activity_status = activity_status;
    }
}
