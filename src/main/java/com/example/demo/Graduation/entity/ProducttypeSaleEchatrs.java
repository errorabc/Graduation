package com.example.demo.Graduation.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ProducttypeSaleEchatrs {
    private String payment_time;//销售时间
    private BigDecimal final_payment;//销售金额

    public String getPayment_time() {
        return payment_time;
    }

    public void setPayment_time(String payment_time) {
        this.payment_time = payment_time;
    }

    public BigDecimal getFinal_payment() {
        return final_payment;
    }

    public void setFinal_payment(BigDecimal final_payment) {
        this.final_payment = final_payment;
    }
}
