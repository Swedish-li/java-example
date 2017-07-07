package com.lrs.common.hamcrest;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

/**
 * 参数化测试
 * 
 * @author Swedish-li
 *
 */
@RunWith(Parameterized.class)
public class ParameterizedTest {

	private int input;

	private boolean isEven;

	public ParameterizedTest(int input, boolean isEven) {
		this.input = input;
		this.isEven = isEven;
	}

	// 测试数据
	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] {
				{ 2, false },
				{ 5, true },
				{ 10, false },
				{ 13, true }
		};
		return Arrays.asList(data);
	}

	@Test
	public void testCheckEven() {
		System.out.printf("测试输入：%s,期望结果：%b\n", input, isEven);

		assertThat(isEven(input), is(isEven));

	}

	/**
	 * 验证是否为奇数
	 * 
	 * @param i
	 * @return
	 */
	private boolean isEven(int i) {
		if (i % 2 == 0) {
			return false;
		} else {
			return true;
		}
	}
}
