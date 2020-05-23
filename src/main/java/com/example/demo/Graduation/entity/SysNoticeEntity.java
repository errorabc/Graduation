package com.example.demo.Graduation.entity;

import java.util.Date;

public class SysNoticeEntity {
    private String id;
    private String name;//公告名
    private String content;//公告内容
    private int states;//公告状态
    private String username;//操作人
    private Date createtime;//创建时间

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStates() {
        return states;
    }

    public void setStates(int states) {
        this.states = states;
    }
}


