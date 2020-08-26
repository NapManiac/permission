package com.fly.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class User implements Serializable {
    private Integer id;
    private Integer deptId;
    private String username;
    private String password;
    private String realname;
    private String  sex;
    private String tel;
    private String email;
    private String avatar;//头像
    private String jobTitle;
    private Integer status;
    private Integer sort;
    private Integer delFlag;
    private Integer createBy;
    private Date createTime;
    private Date updateTime;
    private String dname;//直接加一个属性 映射到部门的名称

    //角色的集合对象
    private List roleList;
}
