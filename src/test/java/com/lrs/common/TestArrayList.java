package com.lrs.common;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Java中ArrayList循环遍历并删除元素的陷阱
 * <p>
 * http://tyrion.iteye.com/blog/2203335
 *
 * @author Swedish-li
 */
public class TestArrayList {
    /**
     * 存在符合条件的数据未删除
     */
    @Test
    public void testErrorRemove1() {
        List<String> list = getSampleList();

        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            if ("bb".equals(str)) {
                list.remove(str);
            }
        }
        System.out.println(list);

    }

    // 使用倒序删除
    @Test
    public void testRemove1() {
        List<String> list = getSampleList();
        for (int i = list.size() - 1; i >= 0; i--) {
            String str = list.get(i);
            if ("bb".equals(str)) {
                list.remove(str);
            }
        }
        System.out.println(list);
    }

    /**
     * 抛出并发修改异常java.util.ConcurrentModificationException
     */
    @Test
    public void testErrRemove2() {
        List<String> list = getSampleList();
        for (String str : list) {
            if ("bb".equals(str)) {
                list.remove(str);
            }
        }
        System.out.println(list);
    }

    // 显式使用迭代器
    @Test
    public void testRemove2() {
        List<String> list = getSampleList();
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String str = iterator.next();
            if ("bb".equals(str)) {
                iterator.remove();
            }
        }
        System.out.println(list);
    }

    private List<String> getSampleList() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("bb");
        list.add("bb");
        list.add("ccc");
        list.add("ccc");
        list.add("ccc");
        return list;
    }
}
