package com.lc.learn.java.tree;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lc 9/2/21 4:20 PM
 */
@Data
public class TreeNode {


    private String id;
    private String name;
    private String parentId;
    private String code;
    private List<TreeNode> children;

    public void addChildren(TreeNode zone) {
        if (children == null) {
            children = new ArrayList<>();
        }
        children.add(zone);
    }






}
