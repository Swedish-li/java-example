package com.lrs.common.hamcrest;

import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

public class RepeatableRule implements MethodRule {

    /**
     * 测试方法重复执行的次数
     */
    private int repeatTimes = 1;

    /**
     * 应用此规则的方法
     */
    private String[] testMethods;

    public RepeatableRule(int repeatTimes, String[] testMethods) {
        super();
        this.repeatTimes = repeatTimes;
        this.testMethods = testMethods;
    }

    @Override
    public Statement apply(final Statement base, final FrameworkMethod method, Object target) {

        return new Statement() {

            @Override
            public void evaluate() throws Throwable {
                if (shouldRepeat(method)) {
                    for (int i = 0; i < repeatTimes; i++) {
                        base.evaluate();
                    }
                } else {
                    base.evaluate();
                }
            }
        };
    }

    private boolean shouldRepeat(FrameworkMethod method) {
        String methodName = method.getName();
        for (String name : testMethods) {
            if (name.equals(methodName)) {
                return true;
            }
        }

        return false;
    }

}
