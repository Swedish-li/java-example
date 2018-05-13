package com.lrs.algorithm.sort;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * 归并排序
 */
public class MergeSort {

    @Test
    public void testMergeSort() {
        int[] arr = new int[]{
                5, 2, 4, 6, 1, 3
        };
        sort(arr);

        assertArrayEquals(new int[]{
                1, 2, 3, 4, 5, 6
        }, arr);

    }

    private void sort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    private void mergeSort(int[] arr, int p, int q) {

        if (p < q) {
            int mid = (q + p) / 2;
            mergeSort(arr, p, mid);
            mergeSort(arr, mid + 1, q);
            merge(arr, p, mid, q);
        }
    }

    private void merge(int[] arr, int p, int q, int r) {
        // r > q > p
        int[] left = getCopyArr(arr, p, q);
        int[] right = getCopyArr(arr, q + 1, r);

        // 记录左右合并到的位置
        int i = 0, j = 0;

        for (int k = p; k < r + 1; k++) {
            if (i >= left.length) {
                System.arraycopy(right, j, arr, k, right.length - j);
                break;
            }
            if (j >= right.length) {
                System.arraycopy(left, i, arr, k, left.length - i);
                break;
            }

            if (left[i] > right[j]) {
                arr[k] = right[j];
                j++;
            } else {
                arr[k] = left[i];
                i++;
            }
        }

    }

    private int[] getCopyArr(final int[] arr, int left, int right) {

        int len = right - left + 1;
        final int[] copyArr = new int[len];
        System.arraycopy(arr, left, copyArr, 0, len);
//        for (int i = 0; i < len; i++) {
//            copyArr[i] = arr[left + i];
//        }

        return copyArr;
    }

}
