package com.fly.po;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString(callSuper = true)
public class NavNode extends Node {
    private String icon="fa fa-window-maximize";
    private String href;
    private String target="_self";
    private List<NavNode> child;

    @Override
    public void setChildren(List children) {
        child = children;
    }

}
