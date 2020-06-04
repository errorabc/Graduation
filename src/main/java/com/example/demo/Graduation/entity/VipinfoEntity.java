package com.example.demo.Graduation.entity;

import java.math.BigDecimal;

public class VipinfoEntity {
    private String id;
    private String name;//VIP名
    private float discount;//折扣
    private BigDecimal miconsumption;//最低消费

    public BigDecimal getMiconsumption() {
        return miconsumption;
    }

    public void setMiconsumption(BigDecimal miconsumption) {
        this.miconsumption = miconsumption;
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


    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }
}
