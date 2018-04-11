package com.lrs.leetcode.tree;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * 平衡二叉树
 * <p>
 * Created by Swedish-li on 2018/3/28.
 */
public class SymmetricTree {

    boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.getLeft(), root.getRight());
    }

    private boolean isMirror(TreeNode t1, TreeNode t2) {

        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }

        return (t1.getVal() == t2.getVal())
                && isMirror(t1.getLeft(), t2.getRight())
                && isMirror(t1.getRight(), t2.getLeft());
    }

    /**
     * 1
     * / \
     * 2   2
     * / \ / \
     * 3  4 4  3
     */
    private TreeNode getSymmetricRoot() {
        TreeNode root = new TreeNode(1);

        TreeNode left = new TreeNode(2);
        left.setLeft(new TreeNode(3));
        left.setRight(new TreeNode(4));

        TreeNode right = new TreeNode(2);
        right.setLeft(new TreeNode(4));
        right.setRight(new TreeNode(3));

        root.setLeft(left);
        root.setRight(right);

        return root;
    }

    /**
     * 1
     * / \
     * 2   2
     * \   \
     * 3    3
     */
    private TreeNode getNotSymmetricRoot() {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        left.setRight(new TreeNode(3));
        TreeNode right = new TreeNode(2);
        right.setRight(new TreeNode(3));

        root.setRight(right);
        root.setLeft(left);
        return root;

    }

    @Test
    public void testIsSymmetric() {
        assertThat(true, is(isSymmetric(getSymmetricRoot())));
        assertThat(true, is(isSymmetricIt(getSymmetricRoot())));

        assertThat(false, is(isSymmetric(getNotSymmetricRoot())));
        assertThat(false, is(isSymmetricIt(getNotSymmetricRoot())));
    }

    boolean isSymmetricIt(TreeNode root) {

        if (root == null) {
            return true;
        }

        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root);
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode t1 = queue.poll();
            TreeNode t2 = queue.poll();

            if (t1 == null && t2 == null) {
                return true;
            }
            if (t1 == null || t2 == null) {
                return false;
            }

            queue.add(t1.getRight());
            queue.add(t2.getLeft());
            queue.add(t1.getLeft());
            queue.add(t2.getRight());
        }

        return true;
    }

}
