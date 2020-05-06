package com.example.demo.Graduation.entity;


import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class PetfosterEntity {

    private String id;//
    private String feedername;//饲养者名称
    private String pettype;//宠物类型
    private String petcage;//宠物笼子编号
    private Date fosterstartime;//寄养开始时间
    private Date fosterendtime;//寄养结束时间
    private String fosterremark;//寄养备注
    private String feederphone;//饲养者手机号
    private BigDecimal fosterprice;//寄养价格
    private int state;//寄养状态   0是寄养者,1是寄养完成
    private int oderstatus;//订单状态   0是生成订单,1是不生成订单

    public int getOderstatus() {
        return oderstatus;
    }

    public void setOderstatus(int oderstatus) {
        this.oderstatus = oderstatus;
    }

    public BigDecimal getFosterprice() {
        return fosterprice;
    }

    public void setFosterprice(BigDecimal fosterprice) {
        this.fosterprice = fosterprice;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getFosterstartime() {
        return fosterstartime;
    }

    public void setFosterstartime(Date fosterstartime) {
        this.fosterstartime = fosterstartime;
    }

    public Date getFosterendtime() {
        return fosterendtime;
    }

    public void setFosterendtime(Date fosterendtime) {
        this.fosterendtime = fosterendtime;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getFeedername() {
        return feedername;
    }

    public void setFeedername(String feedername) {
        this.feedername = feedername;
    }


    public String getPettype() {
        return pettype;
    }

    public void setPettype(String pettype) {
        this.pettype = pettype;
    }


    public String getPetcage() {
        return petcage;
    }

    public void setPetcage(String petcage) {
        this.petcage = petcage;
    }


    public String getFosterremark() {
        return fosterremark;
    }

    public void setFosterremark(String fosterremark) {
        this.fosterremark = fosterremark;
    }


    public String getFeederphone() {
        return feederphone;
    }

    public void setFeederphone(String feederphone) {
        this.feederphone = feederphone;
    }

}
