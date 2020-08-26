package com.fly.po;

import lombok.Data;

import java.util.Date;

@Data
public class UserRole {
    private Integer id;
    private Integer roleId;
    private Integer userId;
    private Date createTime;

    public UserRole(Integer roleId, Integer userId) {
        this.roleId = roleId;
        this.userId = userId;
    }
}
