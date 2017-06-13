package com.lrs.common.decimal;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestNumber {

	private final static double DELTA = 0.0001;

	/**
	 * float的字面量中小数点前的可以省略
	 */
	@Test
	public void testFloat() {
		// 差值小于或等于0.01
		assertEquals(0.75F, .75009F, DELTA);
		assertEquals(0.75F, .74999F, DELTA);
	}
}
