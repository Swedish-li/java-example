package com.lrs.common.mock;

import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
/**
 * 使用@Spy或spy()来监视对象
 * 
 * @author Swedish-li
 *
 */
public class SpyTest {
	@Test
	public void simple() {
		List<String> spyList = spy(new LinkedList<String>());
		// 打桩
		doReturn("foo").when(spyList).get(0);
		
		// 下面代码不生效
		// 真正的方法会被调用
		// 将会抛出 IndexOutOfBoundsException 的异常，因为 List 为空
		// when(spyList.get(0)).thenReturn("foo");
	}
	
	
}
