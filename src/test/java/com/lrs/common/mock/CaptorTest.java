package com.lrs.common.mock;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * 使用Captor(捕捉者)进行异步单元测试
 * <p>
 * https://github.com/android10/Inside_Android_Testing
 *
 * @author Swedish-li
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
        // 初始化测试类
        dummyCaller = new DummyCaller(mockDummyCollaborator);
    }

    @Test
    public void testDoSomethingAsynchronouslyUsingDoAnswer() {
        final List<String> results = Arrays.asList("One", "Two", "Three");
        // 回调函数行为模拟
        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                ((DummyCallback) invocation.getArguments()[0]).onSuccess(results);
                return null;
            }
        }).when(mockDummyCollaborator).doSomethingAsynchronously(
                any(DummyCallback.class));

        // 调用被测试的函数
        dummyCaller.doSomethingAsynchronously();

        // 验证状态与结果
        verify(mockDummyCollaborator, times(1)).doSomethingAsynchronously(
                any(DummyCallback.class)
        );
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
