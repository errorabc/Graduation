package com.example.demo.Graduation.entity.Until;

import com.example.demo.Graduation.entity.UserEntity.UserEntity;

import java.io.Serializable;

public class JstreeVO implements Serializable {
    private String id;
    private String text;
    private String parent;
    private UserEntity userEntity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
