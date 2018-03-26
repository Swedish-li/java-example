package com.lrs.leetcode.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Swedish-li on 2018/3/26.
 */
public class PostorderSolution {

    static List<Integer> recursiveTraversal(TreeNode root) {
        final List<Integer> result = new LinkedList<>();
        recursive(result, root);
        return result;
    }

    private static void recursive(final List<Integer> list, TreeNode node) {
        TreeNode left = node.getLeft();

        TreeNode right = node.getRight();

        if (left != null) {
            recursive(list, left);
        }
        if (right != null) {
            recursive(list, right);
        }

        list.add(node.getVal());

    }

    static List<Integer> iterativeTraversal(TreeNode root) {
        final LinkedList<Integer> result = new LinkedList<>();

        if (root == null) {
            return result;
        }
        final Stack<TreeNode> stack = new Stack<>();

        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            // LinkList.addFirst
            result.addFirst(current.getVal());

            TreeNode left = current.getLeft();
            if (left != null) {
                stack.push(left);
            }

            TreeNode right = current.getRight();
            if (right != null) {
                stack.push(right);
            }
        }
        return result;
    }
}
