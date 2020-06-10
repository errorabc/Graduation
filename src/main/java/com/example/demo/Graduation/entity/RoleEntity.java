package com.example.demo.Graduation.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class RoleEntity {
    private String id;
    private String name;//名称
    private String description;//备注
    private Date createtime;
    private Date updatetime;
    private Long roletree;

    public Long getRoletree() {
        return roletree;
    }

    public void setRoletree(Long roletree) {
        this.roletree = roletree;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}
