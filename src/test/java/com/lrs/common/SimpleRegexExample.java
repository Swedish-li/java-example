package com.lrs.common;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * java正则表达式简单示例
 * 
 * Pattern:正则规范 Matcher:执行规范，使用Pattern规定好的规则进行校验 常用正则规则： \d : 数字 \D : 非数字 \w
 * :字母，数字，下划线 \W : 非字母数字下划线
 * 
 * 
 * junit中的断言和注解 ：http://blog.csdn.net/u014042146/article/details/49465917
 * 
 * @author liruishi
 *
 */
public class SimpleRegexExample {
	private boolean isNumWithNoRegex(String str) {
		boolean flag = true;
		char[] chars = str.toCharArray();
		for (char ch : chars) {
			if (ch < '0' || ch > '9') {
				flag = false;
				break;
			}
		}
		return flag;
	}

	@Test
	public void testIsNumberWithNoRegex() {
		String str = "1234567890";
		assertTrue(isNumWithNoRegex(str));
		assertFalse(isNumWithNoRegex("ab1234"));
	}

	/**
	 * Pattern的静态方法compile为指定正则表达式规则，生成Pattern实例
	 * Pattern的非静态方法matcher根据指定的字符串输入，生成Matcher实例 Matcher的非静态方法matches执行字符串验证
	 */
	@Test
	public void simpleRegexExample() {
		// 判断是否为合法日期格式
		String str = "2016-12-25";
		Pattern pattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
		Matcher matcher = pattern.matcher(str);
		assertTrue(matcher.matches());
	}

	private boolean isNumWithRegex(String str) {
		return Pattern.compile("[0-9]+").matcher(str).matches();
	}

	@Test
	public void testIsNumWithRegex() {
		assertTrue(isNumWithRegex("1234567890"));
		assertFalse(isNumWithRegex("abc133  "));
	}

	// Pattern 进行字符串拆分
	@Test
	public void testSplitString() {
		String str = "abb1122ccs32ddd";
		Pattern pattern = Pattern.compile("\\d+");
		String[] strs = pattern.split(str);
		assertArrayEquals(strs, new String[] { "abb", "ccs", "ddd" });
	}

	// Matcher中的字符串替换功能
	@Test
	public void testReplaceStringInMatcher() {
		String str = "aaa123bbb135ccc";
		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher(str);
		String s = matcher.replaceAll("---");
		assertEquals(s, "aaa---bbb---ccc");

	}

	// 直接使用字符String类中提供的正则支持
	// split,replaceAll,matches,
	/**
	 * 系统配置 user.country:CN java.vm.version:24.45-b08
	 */
	@Test
	public void testSystemProperties() {
		Properties properties = System.getProperties();
		for (Entry<Object, Object> entry : properties.entrySet()) {
			System.out.println(entry.getKey() + "=>" + entry.getValue());
		}
	}

	/**
	 * 解析注解
	 * 
	 * @throws ClassNotFoundException
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws NoSuchMethodException
	 */
	@Test
	public void parseAnnootation()
			throws ClassNotFoundException, NoSuchFieldException, SecurityException, NoSuchMethodException {
		Class<?> clazz = Class.forName("com.lrs.regex.SimpleRegexExample$App");
		MethodInfo methodInfo = clazz.getAnnotation(MethodInfo.class);
		// 类上的注解
		if (methodInfo != null) {
			String val = methodInfo.value();
			System.out.println(val);
			assertEquals("App-info", val);
		}
		// 属性上的注解
		Field field = clazz.getDeclaredField("name");
		field.setAccessible(true);
		MethodInfo fieldMethodInfo = field.getAnnotation(MethodInfo.class);
		if (fieldMethodInfo != null) {
			String val = fieldMethodInfo.value();
			System.out.println(val);
			assertEquals("name-info", val);
		}
		// 构造器上的注解
		Constructor<?> constructor = clazz.getConstructor();
		MethodInfo csMethodInfo = constructor.getAnnotation(MethodInfo.class);
		if (csMethodInfo != null) {
			String val = csMethodInfo.value();
			System.out.println(val);
			assertEquals("constructor-info", val);
		}
		// 方法上的注解
		Method method = clazz.getMethod("getName");
		MethodInfo mtMethodInfo = method.getAnnotation(MethodInfo.class);
		if (mtMethodInfo!=null) {
			String val = mtMethodInfo.value();
			System.out.println(val);
			assertEquals("get-name-info", val);
		}
	}

	// 静态内部类
	// 在类上的注解
	@MethodInfo("App-info")
	public static class App {
		@MethodInfo("constructor-info")
		public App() {

		}

		@MethodInfo("name-info")
		private String name;

		@MethodInfo("get-name-info")
		public String getName() {
			return name;
		}

	}
}
