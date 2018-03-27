package com.lrs.leetcode.tree;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * binary tree traversal
 * <p>
 * Created by Swedish-li on 2018/3/26.
 */
public class SolutionTest {

    /*
                 6
               /  \
             2     7
           /  \     \
          1    4     9
              / \   /
             3  5  8
    */

    private TreeNode getRoot() {
        TreeNode root = new TreeNode(6);
        // 1,2,3,4,5
        TreeNode node1 = new TreeNode(2);
        root.setLeft(node1);

        node1.setLeft(new TreeNode(1));
        TreeNode node2 = new TreeNode(4);
        node2.setLeft(new TreeNode(3));
        node2.setRight(new TreeNode(5));
        node1.setRight(node2);

        TreeNode node3 = new TreeNode(7);
        root.setRight(node3);

        TreeNode node4 = new TreeNode(9);
        node3.setRight(node4);
        node4.setLeft(new TreeNode(8));


        return root;
    }

    // [6,2,1,4,3,5,7,9,8]
    @Test
    public void testRecursivePre() {
        List<Integer> rs = PreorderSolution.recursiveTraversal(getRoot());
        System.out.println(rs);
        assertTrue(Arrays.asList(6, 2, 1, 4, 3, 5, 7, 9, 8).equals(rs));

    }

    @Test
    public void testIterativePre() {
        List<Integer> rs = PreorderSolution.iterativeTraversal(getRoot());
        System.out.println(rs);
        assertTrue(Arrays.asList(6, 2, 1, 4, 3, 5, 7, 9, 8).equals(rs));
    }


    @Test
    public void testRecursiveIn() {
        List<Integer> rs = InorderSolution.recursiveTraversal(getRoot());
        System.out.println(rs);
        assertTrue(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9).equals(rs));
    }

    @Test
    public void testIterativeIn() {
        List<Integer> rs = InorderSolution.iterativeTraversal(getRoot());
        System.out.println(rs);

        assertTrue(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9).equals(rs));
    }

    // 1,3,5,4,2,8,9,7,6

    @Test
    public void recursivePost() {
        List<Integer> rs = PostorderSolution.recursiveTraversal(getRoot());

        System.out.println(rs);

        assertTrue(Arrays.asList(1, 3, 5, 4, 2, 8, 9, 7, 6).equals(rs));
    }

    @Test
    public void itrativePost() {
        List<Integer> rs = PostorderSolution.iterativeTraversal(getRoot());

        System.out.println(rs);

        assertTrue(Arrays.asList(1, 3, 5, 4, 2, 8, 9, 7, 6).equals(rs));
    }

    @Test
    public void topDownMaximumDeptTest() {
        RecursiveSolution solution = new RecursiveSolution();

        solution.maxiumDepth(getRoot(), 1);

        assertThat(solution.getAnswer(), is(4));
    }

    @Test
    public void bottomUpMaximumDepthTest() {
        int depth = RecursiveSolution.maxiumDepth(getRoot());
        assertThat(depth, is(4));
    }
}
