package com.lrs.leetcode.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 递归的方式遍历
 * <p>
 * Created by Swedish-li on 2018/3/26.
 */
public class PreorderSolution {
    /**
     * Iterative method with Stack:
     */
    public static List<Integer> iterativeTraversal(TreeNode root) {
        final List<Integer> result = new LinkedList<>();

        if (root == null) {
            return result;
        }

        final Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.getVal());

            TreeNode right = node.getRight();
            if (right != null) {
                stack.push(right);
            }

            TreeNode left = node.getLeft();
            if (left != null) {
                stack.push(left);
            }
        }


        return result;
    }

    public static List<Integer> recursiveTraversal(TreeNode root) {
        final List<Integer> result = new LinkedList<>();
        recursive(result, root);
        return result;
    }

    private static void recursive(final List<Integer> list, TreeNode node) {

        if (node == null) {
            throw new NullPointerException("tree node can not be null!");
        }

        if (list == null) {
            throw new NullPointerException("result list can not be null!");
        }

        list.add(node.getVal());
        TreeNode left = node.getLeft();
        TreeNode right = node.getRight();
        if (left != null) {
            recursive(list, left);
        }
        if (right != null) {
            recursive(list, right);
        }
    }
}
