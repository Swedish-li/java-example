package com.lrs.common.sorts;

import org.junit.Test;

import static com.lrs.common.sorts.Util.swap;

/**
 * 选择排序
 *
 * @author Swedish-li
 */
public class SelectionSort extends BaseSort {

    @Override
    void sort(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            // 最小数所在位置索引
            int min = i;
            for (int j = i + 1; j < len; j++) {
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            swap(min, i, arr);

        }

    }

    @Override
    @Test
    public void test() {
        testSort();

    }
}
