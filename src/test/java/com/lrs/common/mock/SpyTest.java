package com.lrs.common.mock;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * 使用@Spy或spy()来监视对象
 *
 * @author Swedish-li
 */
@RunWith(MockitoJUnitRunner.class)
public class SpyTest {
    @Spy
    private List<String> list = new ArrayList<>();
    @Mock
    private ArrayList<String> mockList;
    @Spy
    private ArrayList<String> spyList = new ArrayList<>();

    @Test
    public void simple() {
        List<String> spyList = spy(new LinkedList<String>());
        // 打桩
        doReturn("foo").when(spyList).get(0);

        // 下面代码不生效
        // 真正的方法会被调用
        // 将会抛出 IndexOutOfBoundsException 的异常，因为 List 为空
        // when(spyList.get(0)).thenReturn("foo");

        assertEquals("foo", spyList.get(0));
    }

    // Mock对象和Spy对象的区别

    @Before
    public void setUp() {
        // 使用MockitoJUnitRunner注解或在这里初始化当前对象
        MockitoAnnotations.initMocks(this);
    }

    /**
     * 使用注解生成监视对象
     */
    @Test
    public void whenUsingTheSpyAnnotation_thenObjectIsSpied() {
        list.add("one");
        list.add("two");

        verify(list).add("one");
        verify(list).add("two");

        assertEquals(2, list.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenCreateMock_thenCreated() {
        mockList.add("one");

        verify(mockList).add("one");

        assertEquals(0, mockList.size());
        // assertNull(list.get(0));
        list.get(0);
    }

    @Test
    public void whenCreateSpy_thenCreate() {
        spyList.add("one");

        verify(spyList).add("one");

        assertEquals(1, spyList.size());
        assertEquals("one", spyList.get(0));
    }
}
