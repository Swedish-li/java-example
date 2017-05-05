package com.lrs.common.mock;

import org.junit.Test;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * 示例来自：http://www.jianshu.com/p/f6e3ab9719b9
 * 
 * @author Swedish-li
 *
 */

public class MockExampleTest {

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
	public void testForIOException() throws IOException  {
		OutputStream mockStream = mock(OutputStream.class);

		doThrow(new IOException()).when(mockStream).close();
		
		OutputStreamWriter writer  = new OutputStreamWriter(mockStream);
		writer.close();

	}
	
}
