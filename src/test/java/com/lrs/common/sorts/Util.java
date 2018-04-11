package com.lrs.common.sorts;

import java.util.Arrays;

public class Util {
    /**
     * 数组中两个位置的数据交换
     *
     * @param index1
     * @param index2
     * @param arr
     */
    public static void swap(int index1, int index2, int[] arr) {
        if (index1 == index2) {
            return;
        }
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    /**
     * 以字符串形式输出数组(Object)描述
     *
     * @param arr
     */
    public static void printArr(Object[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 以字符串形式输出数组(int)描述
     *
     * @param arr
     */
    public static void printArr(int[] arr) {
        System.out.println(Arrays.toString(arr));

    }

    /**
     * 格式化输出
     *
     * @param msg
     * @param args
     */
    public static void print(String msg, Object... args) {
        System.out.printf(msg, args);
    }

}
