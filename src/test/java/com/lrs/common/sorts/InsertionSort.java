package com.lrs.common.sorts;

import org.junit.Test;

import static com.lrs.common.sorts.Util.*;

/**
 * 插入排序
 * 
 * @author Swedish-li
 *
 */
public class InsertionSort extends BaseSort {

	@Override
	void sort(int[] arr) {
		int len = arr.length;
		for (int i = 0; i < len - 1; i++) {
			for (int j = i + 1; j > 0; j--) {
				if (arr[j - 1] <= arr[j]) {
					break;
				}
				swap(j - 1, j, arr);
			}
		}
	}

	@Test
	@Override
	public void test() {
		testSort();
	}

}
