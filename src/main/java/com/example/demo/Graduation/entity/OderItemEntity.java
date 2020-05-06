package com.example.demo.Graduation.entity;

import java.math.BigDecimal;
import java.util.Date;

public class OderItemEntity {

    private String id;
    private String member_name;
    private String oder_no;
    private String product_id;
    private String product_name;
    private String product_type;
    private BigDecimal current_oder_price;
    private int number;
    private BigDecimal total_price;
    private Date create_time;
    private Date update_time;

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
