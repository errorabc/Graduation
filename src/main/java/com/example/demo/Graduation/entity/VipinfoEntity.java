package com.example.demo.Graduation.entity;

import java.math.BigDecimal;

public class VipinfoEntity {
    private String id;
    private String name;//会员名
    private BigDecimal lowestconsumption;//年最低消费
    private float discount;//折扣率

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

    public BigDecimal getLowestconsumption() {
        return lowestconsumption;
    }

    public void setLowestconsumption(BigDecimal lowestconsumption) {
        this.lowestconsumption = lowestconsumption;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }
}
