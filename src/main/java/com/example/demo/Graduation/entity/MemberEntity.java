package com.example.demo.Graduation.entity;

import java.math.BigDecimal;

public class MemberEntity {
    private String id;//id
    private String name;//会员名
    private String phone;//会员手机
    private String email;//会员邮箱
    private BigDecimal balance;//账户余额
    private VipinfoEntity vip;
    private MemberVipEntity mv;
    private int integral;//会员积分
    private BigDecimal total_consumption;//总消费

    public BigDecimal getTotal_consumption() {
        return total_consumption;
    }

    public void setTotal_consumption(BigDecimal total_consumption) {
        this.total_consumption = total_consumption;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public MemberVipEntity getMv() {
        return mv;
    }

    public void setMv(MemberVipEntity mv) {
        this.mv = mv;
    }

    public VipinfoEntity getVip() {
        return vip;
    }

    public void setVip(VipinfoEntity vip) {
        this.vip = vip;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
