package com.example.demo.Graduation.entity;

public class MemberEntity {
    private String id;
    private String name;
    private String phone;
    private String email;
    private VipinfoEntity vip;
    private MemberVipEntity mv;

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
