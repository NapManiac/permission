package com.fly.po;

import lombok.Data;

import java.util.List;

/***
 * 树结构节点
 */
@Data
public class Node {
    private Integer id;
    private Integer parentId;
    private String title;
    private List<Node> children;
    private Boolean checked;//是否选中

}
