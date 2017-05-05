package com.lrs.common.mock;

import org.junit.Test;
import org.mockito.Matchers;

import static org.mockito.Mockito.*;

public class VerifyExampleTest {
	@Test
	public void testVerify() {
		MyClass test = mock(MyClass.class);
		
		when(test.getUniqueId()).thenReturn(43);
		
		test.test(1);
		test.getUniqueId();
		test.getUniqueId();
		
		// 方法调用校验
		verify(test).test(Matchers.eq(1));
		
		// 调用次数校验
		verify(test,times(2)).getUniqueId();
		
		// never()
		// atLeastOnece()
		// times(5)
		// atMost(3)
		
		
		
	}
}
