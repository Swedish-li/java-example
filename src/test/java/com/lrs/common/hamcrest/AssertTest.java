package com.lrs.common.hamcrest;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import static org.hamcrest.Matchers.*;

public class AssertTest {
	/**
	 * 基本断言
	 * 
	 * message:是个可选的消息，假如提供，将会在发生错误时报告这个消息。
	 */
	@Test
	public void testBase() {
		assertThat(1, is(1));
		assertThat(2, not(3));
		assertThat(8, anything());
	}

	// assertThat(T actual, Matcher<T> matcher);
	// actual实际值，matcher为使用Hamcrest的匹配符来表达变量actual期望值的声明
	@Test
	public void testString() {
		String str = "Hello World!";
		assertThat(str, is("Hello World!"));
		assertThat(str, startsWith("Hello"));
		assertThat(str, endsWith("!"));
		assertThat(str, containsString("World"));
		assertThat(str, equalTo("Hello World!"));

		// hamcrest-library
		assertThat("HELLO WORLD!", equalToIgnoringCase(str));
		// 忽略两端的空白，类似trim后equal
		assertThat(" Hello World!    ", equalToIgnoringWhiteSpace(str));
		assertThat("", isEmptyString());
		assertThat(null, isEmptyOrNullString());
		assertThat("", isEmptyOrNullString());

		// 断言实际值至少满足anyOf中的一个条件
		assertThat("myValue", anyOf(startsWith("foo"), containsString("Val")));
		// 满足allOf中的所有条件
		assertThat(str, allOf(startsWith("Hel"), endsWith("d!")));
	}

	@Test
	public void testIsNull() {
		Object ob = null;
		assertThat(ob, is(nullValue(Object.class)));

		String key = "key1";
		assertThat(key, is(notNullValue(String.class)));
	}

	@Test
	public void iterableTest() {
		List<String> list = Arrays.asList("body", "book", "boss");

		assertThat(list, everyItem(startsWith("bo")));
		assertThat(list, hasItem("body"));
	}

	@Test
	public void testNumber() {
		assertThat(3.2, greaterThan(1.2));
		assertThat(3.8, greaterThanOrEqualTo(3.8));
		assertThat(19, lessThan(20));
		assertThat(60, lessThanOrEqualTo(60));

		assertThat(3.5, closeTo(3.2, 0.3));
		assertThat(new BigDecimal(1.89), closeTo(new BigDecimal(1.8), new BigDecimal("0.09")));

	}

	@Test
	public void mapTest() {
		Map<String, String> map = new HashMap<>();
		map.put("key1", "value1");
		map.put("hello", "world");
		assertThat(map, hasEntry("key1", "value1"));

		assertThat(map, hasKey("key1"));
		assertThat(map, hasValue("world"));

	}

	// 数组断言
	@Test
	public void arrayTest() {
		String[] strArr = { "ab", "hello", "world!" };

		// size断言
		assertThat(strArr, arrayWithSize(3));
		// 两个数组完全相等
		assertThat(strArr, arrayContaining("ab", "hello", "world!"));
		// 数据一致，顺序可以不一致
		assertThat(strArr, arrayContainingInAnyOrder("hello", "ab", "world!"));
		// 集合
		assertThat("world!", isIn(Arrays.asList(strArr)));
		// 数组
		assertThat("hello", isOneOf(strArr));
	}

}
