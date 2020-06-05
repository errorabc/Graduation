package com.example.demo.Graduation.entity;

import java.util.Date;

//预约
public class Reservation {
    private String id;
    private String reservation_name;//预约人
    private Date reservation_time;//预约时间
    private String reservation_type;//预约类型
    private String reservation_content;//预约内容
    private int status;//预约状态
    private String time;
    private String phone;//手机

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReservation_name() {
        return reservation_name;
    }

    public void setReservation_name(String reservation_name) {
        this.reservation_name = reservation_name;
    }

    public Date getReservation_time() {
        return reservation_time;
    }

    public void setReservation_time(Date reservation_time) {
        this.reservation_time = reservation_time;
    }

    public String getReservation_type() {
        return reservation_type;
    }

    public void setReservation_type(String reservation_type) {
        this.reservation_type = reservation_type;
    }

    public String getReservation_content() {
        return reservation_content;
    }

    public void setReservation_content(String reservation_content) {
        this.reservation_content = reservation_content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
