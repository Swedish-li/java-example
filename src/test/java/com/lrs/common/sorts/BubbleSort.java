package com.lrs.common.sorts;

import org.junit.Test;

import static com.lrs.common.sorts.Util.*;

/**
 * 冒泡排序
 * 
 * ASC:升序（默认） DESC:降序
 * 
 * @author Swedish-li
 *
 */
public class BubbleSort extends BaseSort {

	@Override
	void sort(int[] arr) {
		int len = arr.length;
		// 冒泡次数
		for (int i = 0; i < len; i++) {
			// 一次冒泡的过程中是否发生过交换
			boolean swapped = false;
			// 冒泡过程
			for (int j = 0; j < len - 1 - i; j++) {
				if (arr[j] > arr[j + 1]) {
					swap(j, j + 1, arr);
					if (!swapped) {
						swapped = true;
					}
				}
			}
			// 未发生交换，数组已为有序，直接返回
			if (!swapped) {
				return;
			}
		}

	}

	@Test
	@Override
	public void test() {
		testSort();

	}

}
