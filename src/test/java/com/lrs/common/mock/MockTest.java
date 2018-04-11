package com.lrs.common.mock;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

/**
 * 使用强大的 Mockito 来测试你的代码
 * <p>
 * http://www.jianshu.com/p/f6e3ab9719b9
 *
 * @author Swedish-li
 */
public class MockTest {
    // mock object 是指类或者接口的模拟实现

    /**
     * 交互行为验证
     */
    @Test
    public void testVerifyInteractions() {
        // 使用mock方法生成mock对象
        @SuppressWarnings("unchecked")
        List<String> list = mock(List.class);

        list.add("one");
        list.clear();

        // 方法执行验证
        verify(list).add("one");
        verify(list).clear();
    }

    /**
     * 行为模拟
     */
    @Test
    public void testStubbing() {
        @SuppressWarnings("unchecked")
        LinkedList<String> linkedList = mock(LinkedList.class);
        // 在执行方法之前做行为模拟
        when(linkedList.get(0)).thenReturn("first");

        assertEquals("first", linkedList.get(0));
        // 未进行模拟的返回null
        assertNull(linkedList.get(8));
    }
}
