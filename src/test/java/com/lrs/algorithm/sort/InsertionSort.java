package com.lrs.algorithm.sort;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * 插入排序
 */
public class InsertionSort<T> {

    enum Type {
        ASC, DESC
    }

    void sort(int[] arr, Type type) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && compare(arr[j], key, type) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    private int compare(int x, int y, Type type) {
        switch (type) {
            case ASC:
                return x - y;
            case DESC:
                return y - x;
            default:
                throw new IllegalArgumentException("sort type can't be null");
        }
    }


    private int[] getData() {
        return new int[]{
                5, 2, 4, 6, 1, 3
        };
    }

    @Test
    public void sortAsc() {
        int[] arr = getData();
        sort(arr, Type.ASC);

        assertArrayEquals(arr, new int[]{
                1, 2, 3, 4, 5, 6
        });

    }

    @Test
    public void sortDesc() {
        int[] arr = getData();
        sort(arr, Type.DESC);

        assertArrayEquals(arr, new int[]{
                6, 5, 4, 3, 2, 1
        });
    }

}
