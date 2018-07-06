package com.lrs.common.sorts;

import org.junit.Test;

import java.util.BitSet;

/**
 * 使用位向量进行排序
 */
public class ByteSetSort extends BaseSort {
    @Override
    void sort(int[] arr) {
        BitSet bitSet = new BitSet(60);
        for (int i : arr) {
            bitSet.set(i);
        }

        int idx = 0;
        for (int j = 0; j < 60; j++) {
            if (bitSet.get(j)) {
                arr[idx] = j;
                idx++;
            }
        }


    }


    @Override
    @Test
    public void test() {
        testSort();
    }
}
