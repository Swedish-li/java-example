package com.lrs.common.mock;

import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InOrder;
import org.mockito.exceptions.verification.NoInteractionsWanted;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
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
        verify(test).test(ArgumentMatchers.eq(1));

        // 调用次数校验
        verify(test, times(2)).getUniqueId();

    }

    // 验证方法执行次数
    @Test
    public void verifying_number_of_invocations() {
        @SuppressWarnings("unchecked")
        List<Integer> list = mock(List.class);
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(3);
        list.add(3);
        list.add(3);

        // 调用一次
        verify(list).add(1);
        verify(list, times(1)).add(1);

        // 调用多次
        verify(list, times(2)).add(2);
        verify(list, times(3)).add(3);

        // 从未被调用
        verify(list, never()).add(4);

        // 至少调用 次数
        verify(list, atLeastOnce()).add(1);
        verify(list, atLeast(2)).add(2);
        // 最多调用次数
        verify(list, atMost(3)).add(3);

    }

    // 验证执行顺序
    @SuppressWarnings("unchecked")
    @Test
    public void verification_in_order() {
        List<Integer> list = mock(List.class);
        List<String> list2 = mock(List.class);

        list.add(1);
        list2.add("one");
        list.add(2);
        list2.add("two");

        InOrder inOrder = inOrder(list, list2);
        inOrder.verify(list).add(1);
        inOrder.verify(list2).add("one");
        inOrder.verify(list).add(2);
        inOrder.verify(list2).add("two");
    }

    // 验证模拟对象的互动
    @SuppressWarnings("unchecked")
    @Test
    public void verifyInteraction() {
        List<String> list = mock(List.class);
        List<String> list2 = mock(List.class);
        List<String> list3 = mock(List.class);

        list.add("1");
        verify(list).add("1");
        verify(list2, never()).add("2");
        verifyZeroInteractions(list3);
    }

    @SuppressWarnings("unchecked")
    @Test(expected = NoInteractionsWanted.class)
    public void find_redundant_interaction() {
        List<Integer> list = mock(List.class);
        List<Integer> list2 = mock(List.class);

        list.add(1);
        list.add(2);

        verify(list, times(2)).add(anyInt());

        verifyNoMoreInteractions(list);

        list2.add(1);
        list2.add(3);
        verify(list2).add(1);
        // 此处会抛出异常
        verifyNoMoreInteractions(list2);

    }
}
