package com.lrs.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Swedish-li on 2018/3/26.
 */
public class InorderSolution {
    public static List<Integer> recursiveTraversal(TreeNode root) {
        final List<Integer> result = new LinkedList<>();
        recursive(result, root);
        return result;
    }

    private static void recursive(final List<Integer> list, TreeNode node) {

        TreeNode left = node.getLeft();
        if (left != null) {
            recursive(list, left);
        }

        list.add(node.getVal());

        TreeNode right = node.getRight();
        if (right != null) {
            recursive(list, right);
        }
    }

    public static List<Integer> iterativeTraversal(TreeNode root) {
        final List<Integer> list = new ArrayList<>();

        TreeNode currentNode = root;
        final Stack<TreeNode> stack = new Stack<>();

        while (currentNode != null || !stack.isEmpty()) {
            while (currentNode != null) {
                // left,root,right
                stack.push(currentNode);
                currentNode = currentNode.getLeft();
            }
            // 此时currentNode为root节点
            currentNode = stack.pop();

            list.add(currentNode.getVal());
            currentNode = currentNode.getRight();

        }

        return list;
    }

}
