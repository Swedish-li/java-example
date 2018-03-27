package com.lrs.leetcode.tree;

/**
 * Solve Tree Problems Recursively
 * <p>
 * Created by Swedish-li on 2018/3/27.
 */
public class RecursiveSolution {

    private int answer = 0;

    // top-down
    public void maxiumDepth(TreeNode root, int depth) {
        if (root == null) {
            return;
        }

        TreeNode left = root.getLeft();
        if (left != null) {
            maxiumDepth(left, depth + 1);
        }

        TreeNode right = root.getRight();
        if (right != null) {
            maxiumDepth(right, depth + 1);
        }

        if (left == null && right == null) {
            this.answer = depth;
        }
    }

    public int getAnswer() {
        return answer;
    }
    // bottom-up
    public static int maxiumDepth(TreeNode root) {

        if (root == null) {
            return 0;
        }

        int depth = 1;

        TreeNode left = root.getLeft();
        if (left != null) {
            depth = maxiumDepth(left) + 1;
        }

        TreeNode right = root.getRight();
        if (right != null) {
            depth = Math.max(depth, maxiumDepth(right) + 1);
        }

        return depth;

    }
}
