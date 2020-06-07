package com.example.demo.Graduation.entity;

import java.math.BigDecimal;
import java.util.Date;

public class OderItemEntity {

    private String id;//ID
    private String member_name;//会员名
    private String oder_no;//订单号
    private String product_id;//商品ID
    private String product_name;//商品名称
    private String product_type;//商品类型
    private BigDecimal current_oder_price;//应付的单价
    private int number;//数量
    private BigDecimal total_price;//应付的总金额
    private Date create_time;//创建时间
    private Date update_time;//更新时间
    private String starttime;
    private String activityname;//参加活动名称

    public String getActivityname() {
        return activityname;
    }

    public void setActivityname(String activityname) {
        this.activityname = activityname;
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

    private String endtime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getOder_no() {
        return oder_no;
    }

    public void setOder_no(String oder_no) {
        this.oder_no = oder_no;
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

    public BigDecimal getCurrent_oder_price() {
        return current_oder_price;
    }

    public void setCurrent_oder_price(BigDecimal current_oder_price) {
        this.current_oder_price = current_oder_price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public BigDecimal getTotal_price() {
        return total_price;
    }

    public void setTotal_price(BigDecimal total_price) {
        this.total_price = total_price;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
}
