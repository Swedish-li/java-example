package com.lrs.common;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SimpleAlgorithms {

    @Test
    public void testCommontDivisor() {
        assertEquals(3, getCommonDivisor(6, 9));

        assertEquals(1, getCommonDivisor(8, 7));
        assertEquals(5, getCommonDivisor(15, 20));
        assertEquals(9, getCommonDivisor(18, 9));
    }

    // 求公约数
    public int getCommonDivisor(int a, int b) {
        // a>0,b>0
        int min = (a > b) ? b : a;

        for (int i = min; i >= 1; i--) {
            if (a % i == 0 && b % i == 0)
                return i;
        }
        return 1;
    }
}
