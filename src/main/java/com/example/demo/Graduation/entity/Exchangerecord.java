package com.example.demo.Graduation.entity;


import java.util.Date;

//积分兑换记录信息表
public class Exchangerecord {

    private String id;//
    private String product_id;//商品ID
    private String product_name;//商品名称
    private String product_type;//商品类型
    private String membername;//兑换会员名
    private int number;//兑换数量
    private Date redeem_time;//兑换时间
    private int redeemintegral;//兑换积分

    public int getRedeemintegral() {
        return redeemintegral;
    }

    public void setRedeemintegral(int redeemintegral) {
        this.redeemintegral = redeemintegral;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
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

    public Date getRedeem_time() {
        return redeem_time;
    }

    public void setRedeem_time(Date redeem_time) {
        this.redeem_time = redeem_time;
    }
}
