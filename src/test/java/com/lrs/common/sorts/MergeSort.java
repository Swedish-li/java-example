package com.lrs.common.sorts;

import org.junit.Test;

/**
 * 归并排序
 * 分治法（Divide and Conquer）典型应用
 * 
 * @author swedish-li
 *
 */
public class MergeSort extends BaseSort {

	@Override
	void sort(int[] arr) {
		int len = arr.length;
		int[] rs = new int[len];
		sortRecursive(arr, rs, 0, len - 1);
	}

	/**
	 * 递归法
	 */
	void sortRecursive(int[] arr, int[] rs, int start, int end) {
		if (start >= end) {
			return;
		}
		// 将数组拆分，在分别归并排序
		int len = end - start;
		int mid = (len >> 1) + start;
		int start1 = start;
		int end1 = mid;
		int start2 = mid + 1;
		int end2 = end;
		// [start,mid],[k,end]
		sortRecursive(arr, rs, start1, end1);
		sortRecursive(arr, rs, start2, end2);
		// 将排序完成的数组合并到rs中
		int j = start;
		while (start1 <= end1 && start2 <= end2) {
			rs[j++] = arr[start1] < arr[start2] ? arr[start1++] : arr[start2++];
		}
		while (start1 <= end1) {
			rs[j++] = arr[start1++];
		}
		while (start2 <= end2) {
			rs[j++] = arr[start2++];
		}
		// 将数据转存会arr
		for (int i = start; i <= end; i++) {
			arr[i] = rs[i];
		}
	}

	@Override
	@Test
	public void test() {
		testSort();

	}

}
