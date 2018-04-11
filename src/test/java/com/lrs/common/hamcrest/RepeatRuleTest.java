package com.lrs.common.hamcrest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.MethodRule;

/**
 * 自定义测试规则示例
 *
 * @author Swedish-li
 */
public class RepeatRuleTest {

    @Rule
    public MethodRule rule = new RepeatableRule(5, new String[]{"repeatedTest"});

    @Test
    public void repeatedTest() {
        System.out.println("这个方法将重复执行5次");
    }

    @Test
    public void notRepeatedTest() {
        System.out.println("这个方法将不会重复执行");
    }
}
