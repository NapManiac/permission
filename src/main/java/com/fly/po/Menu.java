package com.fly.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Menu implements Serializable {
    private Integer id;
    private Integer parentId;
    private Integer type;
    private String name;
    private String permission;
    private String url;
    private Integer status;
    private Integer sort;
    private Integer createBy;
    private Date createTime;
    private Date updateTime;
}
