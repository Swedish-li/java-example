package com.lrs.leetcode.tree;

/**
 * Definition for a binary tree node
 * <p>
 * Created by Swedish-li on 2018/3/26.
 */
public class TreeNode {
    private int val;

    private TreeNode left;
    private TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}
