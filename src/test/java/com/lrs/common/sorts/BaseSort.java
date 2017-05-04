package com.lrs.common.sorts;

import static com.lrs.common.sorts.Util.print;
import static com.lrs.common.sorts.Util.printArr;
import static org.junit.Assert.*;

import org.apache.commons.lang3.time.StopWatch;

/**
 * 排序算法学习 算法（アルゴリズム，Algorithm） 参考文章：https://github.com/damonare/Sorts
 * 
 * @author Swedish-li
 *
 */
public abstract class BaseSort {

	private final int[] actualArr = { 2, 3, 4, 5, 15, 19, 26, 27, 36, 38, 44, 46, 47, 48, 50 };
	// 测试数组
	private final int[] sortArr = { 3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48 };

	/**
	 * 升序排序（ASC）
	 * 
	 * @param arr
	 */
	abstract void sort(int[] arr);

	protected void testSort() {
		StopWatch watch = StopWatch.createStarted();
		sort(sortArr);
		watch.stop();
		// 断言
		assertArrayEquals(sortArr, actualArr);
		printArr(sortArr);
		print("花费时间(纳秒):%d", watch.getNanoTime());
	}

	/**
	 * 执行测试方法
	 */
	public abstract void test();
}
