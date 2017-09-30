package com.lrs.common.mock.power;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.hamcrest.Matchers.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ StringUtils.class, ArrayUtils.class })
public class PowerMockTest {

	@Test
	public void testMockStaticMethod() {
		PowerMockito.mockStatic(StringUtils.class, ArrayUtils.class);

		Mockito.when(StringUtils.capitalize(Mockito.anyString()))
				.thenReturn("capitalize");

		char[] arr = "indexof".toCharArray();

		// 两个参数必须都为Matcher
		Mockito.when(ArrayUtils.indexOf(Mockito.eq(arr), Mockito.anyChar()))
				.thenReturn(8);

		Assert.assertThat(StringUtils.capitalize(""), equalTo("capitalize"));

		Assert.assertThat(ArrayUtils.indexOf(arr, 's'), equalTo(8));

	}

	// verify behavior
	@Test
	public void testVerifyStatic() {

		PowerMockito.mockStatic(StringUtils.class);

		PowerMockito.verifyStatic(StringUtils.class);

		//StringUtils.trim(Mockito.anyString());

		MockTest.staticMethod();
		PowerMockito.verifyStatic(StringUtils.class, Mockito.times(1));
		

	}

}
