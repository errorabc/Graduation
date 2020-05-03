package com.sample;


import java.math.BigDecimal;
import java.util.Date;

public class Oderitem {

  private String id;
  private String userid;
  private String oderNo;
  private String productId;
  private String productName;
  private String productType;
  private BigDecimal currentOderPrice;
  private int number;
  private BigDecimal totalPrice;
  private Date createTime;
  private Date updateTime;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUserid() {
    return userid;
  }

  public void setUserid(String userid) {
    this.userid = userid;
  }

  public String getOderNo() {
    return oderNo;
  }

  public void setOderNo(String oderNo) {
    this.oderNo = oderNo;
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

  public BigDecimal getCurrentOderPrice() {
    return currentOderPrice;
  }

  public void setCurrentOderPrice(BigDecimal currentOderPrice) {
    this.currentOderPrice = currentOderPrice;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public BigDecimal getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(BigDecimal totalPrice) {
    this.totalPrice = totalPrice;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }
}
