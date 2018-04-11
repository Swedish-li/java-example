package com.lrs.common.sorts;

import org.junit.Test;

/**
 * 希尔排序(递减增量排序算法)
 * <p>
 * 将带排序序列分割成若干个子序列进行排序
 *
 * @author Swedish-li
 */
public class ShellSort extends BaseSort {

    @Override
    void sort(int[] arr) {
        int len = arr.length;
        // 步长序列
        int gap = 1;
        // 根据数组长度获取步长序列
        while (gap < len / 3) {
            gap = gap * 3 + 1;
        }
        for (; gap > 0; gap /= 3) {
            for (int i = gap; i < len; i++) {
                int temp = arr[i];
                int j = 0;
                for (j = i - gap; j >= 0 && arr[j] > temp; j -= gap) {
                    arr[j + gap] = arr[j];
                }
                arr[j + gap] = temp;
            }
        }

    }

    @Override
    @Test
    public void test() {
        testSort();

    }

}
