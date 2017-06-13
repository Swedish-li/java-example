package com.lrs.common.mock;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.lrs.model.Brand;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.*;

/**
 * 示例来自：http://www.jianshu.com/p/f6e3ab9719b9
 * 
 * @author Swedish-li
 *
 */

public class MockExampleTest {

	// void 方法
	@Test
	public void shouldCallSet() {
		Brand brand = mock(Brand.class);
		brand.setId(183L);
		verify(brand, times(1)).setId(183L);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowException() {
		Brand brand = mock(Brand.class);
		doThrow(new IllegalArgumentException("arg should not be blank")).when(brand).setDescription("");
		// doNothing().doThrow(new IllegalArgumentException("arg should not be
		// blank")).when(brand).setDescription("");
		brand.setDescription("");

	}

	@Test(expected = IllegalStateException.class)
	public void shouldThrowIllegalStateException() {
		HttpServletResponse res = mock(HttpServletResponse.class);
		// 只有void方法可以使用 doNothing
		doNothing().doThrow(new IllegalStateException("can't repeat set content-type"))
				.when(res).setContentType(anyString());
		res.setContentType("text/xml");
		res.setContentType("application/pdf");
	}

	@Test
	public void shouldCallRealMethod() {
		Brand brand = mock(Brand.class);

		doCallRealMethod().when(brand).setId(1L);
		doCallRealMethod().when(brand).getId();
		brand.setId(1L);

		assertEquals(Long.valueOf(1L), brand.getId());

	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldAnswer() {
		HttpServletResponse res = mock(HttpServletResponse.class);

		doAnswer(new Answer<Void>() {

			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				HttpServletResponse mockRes = (HttpServletResponse) invocation.getMock();
				when(mockRes.getContentType()).thenReturn("application/octet-stream;charset=utf-8");
				return null;
			}
		}).when(res).setContentType("application/octet-stream;charset=utf-8");

		res.setContentType("application/octet-stream;charset=utf-8");
		assertEquals("application/octet-stream;charset=utf-8", res.getContentType());

		// =======================================================
		doAnswer(new Answer<String>() {

			@Override
			public String answer(InvocationOnMock invocation) throws Throwable {

				// 获取方法执行参数
				Object[] args = invocation.getArguments();
				String arg = (String) args[0];
				if (StringUtils.isBlank(arg)) {
					throw new IllegalArgumentException("arg can't be blank!");
				}
				return "header-content";
			}
		}).when(res).getHeader(anyString());

		assertEquals("header-content", res.getHeader("content"));
		res.getHeader("");
	}

	@Test
	public void test1() {
		MyClass test = mock(MyClass.class);

		when(test.getUniqueId()).thenReturn(43);

		assertEquals(Integer.valueOf(43), test.getUniqueId());
	}

	// 返回多个对象的测试
	@Test
	public void testMoreThanOneReturnValue() {
		@SuppressWarnings("unchecked")
		Iterator<String> iterator = mock(Iterator.class);
		when(iterator.next()).thenReturn("Mockito").thenReturn("rocks");

		String rs = iterator.next() + " " + iterator.next();
		assertEquals("Mockito rocks", rs);
	}

	// 根据输入来返回值
	@Test
	public void testReturnValueDependentOnMethodParameter() {
		@SuppressWarnings("unchecked")
		Comparable<String> comparable = mock(Comparable.class);

		when(comparable.compareTo("Mockito")).thenReturn(1);
		when(comparable.compareTo("Test")).thenReturn(-1);

		assertEquals(1, comparable.compareTo("Mockito"));
		assertEquals(-1, comparable.compareTo("Test"));
	}

	// 让返回值不依赖于输入
	@Test
	public void testReturnValueInDependentOnMethodParameter() {
		@SuppressWarnings("unchecked")
		Comparable<Integer> comparable = mock(Comparable.class);

		when(comparable.compareTo(anyInt())).thenReturn(-1);

		assertEquals(-1, comparable.compareTo(10));
	}

	// 异常模拟
	@Test(expected = IOException.class)
	public void testForIOException() throws IOException {
		OutputStream mockStream = mock(OutputStream.class);

		doThrow(new IOException()).when(mockStream).close();

		OutputStreamWriter writer = new OutputStreamWriter(mockStream);
		writer.close();

	}

}
