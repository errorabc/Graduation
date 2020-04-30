package com.example.demo.Graduation.entity;


import java.math.BigDecimal;

public class PetjewelryEntity {

    private String id;
    private String petjewelryname;
    private String petjewelrytype;
    private BigDecimal petjewelryprice;
    private int petjewelrynumber;

    public int getPetjewelrynumber() {
        return petjewelrynumber;
    }

    public void setPetjewelrynumber(int petjewelrynumber) {
        this.petjewelrynumber = petjewelrynumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getPetjewelryname() {
        return petjewelryname;
    }

    public void setPetjewelryname(String petjewelryname) {
        this.petjewelryname = petjewelryname;
    }


    public String getPetjewelrytype() {
        return petjewelrytype;
    }

    public void setPetjewelrytype(String petjewelrytype) {
        this.petjewelrytype = petjewelrytype;
    }


    public BigDecimal getPetjewelryprice() {
        return petjewelryprice;
    }

    public void setPetjewelryprice(BigDecimal petjewelryprice) {
        this.petjewelryprice = petjewelryprice;
    }


}
