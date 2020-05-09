package com.example.demo.Graduation.entity;

import java.math.BigDecimal;
import java.util.Date;

public class OderEntity {
    private String id;//ID
    private String oder_no;//订单号
    private String oder_type;//订单类型
    private String member_name;//用户名称
    private BigDecimal payment;//实际支付金额
    private String payment_type;//支付方式
    private int status;//订单状态  0是未支付,1是支付失败，2是支付成功，3是交易关闭
    private Date payment_time;//支付时间
    private Date create_time;//创建时间
    private Date update_time;//修改时间
    private String remark;//备注
    private String starttime;
    private String endtime;
    private OderItemEntity oderi;

    public OderItemEntity getOderi() {
        return oderi;
    }

    public void setOderi(OderItemEntity oderi) {
        this.oderi = oderi;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOder_no() {
        return oder_no;
    }

    public void setOder_no(String oder_no) {
        this.oder_no = oder_no;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getPayment_time() {
        return payment_time;
    }

    public void setPayment_time(Date payment_time) {
        this.payment_time = payment_time;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOder_type() {
        return oder_type;
    }

    public void setOder_type(String oder_type) {
        this.oder_type = oder_type;
    }
}
