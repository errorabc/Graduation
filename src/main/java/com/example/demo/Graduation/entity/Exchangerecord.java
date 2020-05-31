package com.example.demo.Graduation.entity;


import java.util.Date;

//积分兑换信息表
public class Exchangerecord {

    private String id;//
    private String productId;//商品ID
    private String productName;//商品名称
    private String productType;//商品类型
    private String membername;//兑换会员名
    private int number;//兑换数量
    private Date redeemTime;//兑换时间


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }


    public String getMembername() {
        return membername;
    }

    public void setMembername(String membername) {
        this.membername = membername;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getRedeemTime() {
        return redeemTime;
    }

    public void setRedeemTime(Date redeemTime) {
        this.redeemTime = redeemTime;
    }
}
