package com.example.demo.Graduation.entity;

//积分兑换信息表
public class IntegralExchange {

    private String id;  //商品ID
    private String name;//商品名称
    private String type;//商品类型
    private int needredeem;//兑换需要的积分
    private int number;//兑换的库存


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


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNeedredeem() {
        return needredeem;
    }

    public void setNeedredeem(int needredeem) {
        this.needredeem = needredeem;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
