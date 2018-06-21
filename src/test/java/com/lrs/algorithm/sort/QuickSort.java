package com.lrs.algorithm.sort;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class QuickSort {

    private int[] arr;

    public QuickSort setArr(int[] arr) {
        this.arr = arr;
        return this;
    }

    void sort() {
        this.quickSort(0, arr.length - 1);
    }

    private void quickSort(int p, int r) {
        if (p < r) {
            int q = partition(p, r);
            quickSort(p, q - 1);
            quickSort(q + 1, r);
        }
    }

    private int partition(int p, int r) {
        int x = this.arr[r];
        int i = p - 1;

        for (int j = p; j <= r - 1; j++) {
            if (this.arr[j] <= x) {
                i++;
                exchange(i, j);
            }
        }
        exchange(i + 1, r);

        return i + 1;
    }

    private void exchange(int i, int j) {
        if (i == j) {
            return;
        }
        int temp = this.arr[i];
        this.arr[i] = this.arr[j];
        this.arr[j] = temp;
    }

    @Test
    public void testQuickSort() {

        int[] arr = new int[]{
                5, 2, 4, 6, 1, 3
        };

        new QuickSort().setArr(arr).sort();

        assertArrayEquals(new int[]{
                1, 2, 3, 4, 5, 6
        }, arr);
    }
}
