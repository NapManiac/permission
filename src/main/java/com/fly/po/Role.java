package com.fly.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Role implements Serializable {
    private Integer id;
    private Integer parentId;
    private Integer type;
    private String name;
    private String remarks;
    private Integer createBy;
    private Date createTime;
    private Date updateTime;
}
