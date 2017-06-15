package com.lrs.common.mock;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsEqual.*;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 * 使用Captor进行异步单元测试
 * 
 * https://github.com/android10/Inside_Android_Testing
 * 
 * @author Swedish-li
 *
 */
public class CaptorTest {
	// 要测试的类型
	private DummyCaller dummyCaller;

	@Mock
	private DummyCollaborator mockDummyCollaborator;

	@Captor
	private ArgumentCaptor<DummyCallback> dummyCallbackArgumentCaptor;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		dummyCaller = new DummyCaller(mockDummyCollaborator);
	}

	@Test
	public void testDoSomethingAsynchronouslyUsingDoAnswer() {
		final List<String> results = Arrays.asList("One", "Two", "Three");
		// 为callback执行一个同步anwser
		doAnswer(new Answer() {
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				((DummyCallback) invocation.getArguments()[0]).onSuccess(results);
				return null;
			}
		}).when(mockDummyCollaborator).doSomethingAsynchronously(
				any(DummyCallback.class));

		// 调用被测试的函数
		dummyCaller.doSomethingAsynchronously();

		// 验证状态与结果
		verify(mockDummyCollaborator, times(1)).doSomethingAsynchronously(
				any(DummyCallback.class));
		assertThat(dummyCaller.getResult(), is(equalTo(results)));
	}

	@Test
	public void testDoSomethingAsynchronouslyUsingArgumentCaptor() {
		// 调用要被测试发函数
		dummyCaller.doSomethingAsynchronously();

		final List<String> results = Arrays.asList("One", "Two", "Three");

		// Let's call the callback. ArgumentCaptor.capture() works like a
		// matcher.
		verify(mockDummyCollaborator, times(1)).doSomethingAsynchronously(
				dummyCallbackArgumentCaptor.capture());

		// 在执行回调之前验证结果
		assertThat(dummyCaller.getResult().isEmpty(), is(true));

		// 调用回调的onSuccess函数
		dummyCallbackArgumentCaptor.getValue().onSuccess(results);

		// 再次验证结果
		assertThat(dummyCaller.getResult(), is(equalTo(results)));
	}
}
