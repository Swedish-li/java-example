package com.lrs.common.sorts;

import static com.lrs.common.sorts.Util.*;

import org.junit.Test;

/**
 * 堆排序是利用堆的性质进行的一种选择排序
 * 
 * 堆实际上是一棵完全二叉树， 其任何一非叶节点的关键字不大于或者不小于其左右孩子节点的关键字
 * 
 * http://blog.csdn.net/xiaoxiaoxuewen/article/details/7570621/
 * 
 * 先访问根，再遍历左子树，再遍历右子树
 * 
 * 将数组按照先序遍历构造堆
 * 
 * @author Swedish-li
 *
 */
public class HeapSort extends BaseSort {

	private int[] arr;

	@Override
	synchronized void sort(int[] arr) {
		/*
		 * 将数组堆化
		 */
		this.arr = arr;
		// max index
		int len = arr.length - 1;
		// 获取第一个非叶节点
		int startIndex = (len - 1) >> 1;
		for (int i = startIndex; i >= 0; i--) {
			maxHeapify(i, len);
		}

		/*
		 * 堆化数据排序
		 */
		for (int i = len; i > 0; i--) {
			swap(0, i, arr);
			// 继续堆化
			maxHeapify(0, i - 1);
		}

	}

	/*
	 * 调整数组，使其符合堆特性（先序遍历）
	 * 
	 * @param index 需要堆化处理的数据的索引
	 * 
	 * @param len 未排序的堆（数组）的长度
	 */
	private void maxHeapify(int index, int len) {
		// 获取当前节点的左右节点
		int li = (index << 1) + 1; // 左子节点索引
		int ri = li + 1; // 右子节点索引
		int cMax = li; // 子节点值最大索引，默认左子节点。

		if (li > len)
			return; // 左子节点索引超出计算范围，直接返回。
		if (ri <= len && arr[ri] > arr[li]) // 先判断左右子节点，哪个较大。
			cMax = ri;
		if (arr[cMax] > arr[index]) {
			swap(cMax, index, arr); // 如果父节点被子节点调换，
			maxHeapify(cMax, len); // 则需要继续判断换下后的父节点是否符合堆的特性。
		}
	}

	@Override
	@Test
	public void test() {
		testSort();

	}

}
