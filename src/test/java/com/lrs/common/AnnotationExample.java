package com.lrs.common;

import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map.Entry;
import java.util.Properties;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 *
 * @author liruishi
 */
public class AnnotationExample {


    /**
     * 系统配置 user.country:CN java.vm.version:24.45-b08
     */
    @Test
    public void testSystemProperties() {
        Properties properties = System.getProperties();
        for (Entry<Object, Object> entry : properties.entrySet()) {
            System.out.println(entry.getKey() + "=>" + entry.getValue());
        }
    }

    /**
     * 解析注解
     *
     * @throws ClassNotFoundException
     * @throws SecurityException
     * @throws NoSuchFieldException
     * @throws NoSuchMethodException
     */
    @Test
    public void parseAnnotation()
            throws ClassNotFoundException, NoSuchFieldException, SecurityException, NoSuchMethodException {
        Class<?> clazz = Class.forName("com.lrs.common.AnnotationExample$App");
        MethodInfo methodInfo = clazz.getAnnotation(MethodInfo.class);
        // 类上的注解
        if (methodInfo != null) {
            String val = methodInfo.value();
            System.out.println(val);
            assertEquals("App-info", val);
        }
        // 属性上的注解
        Field field = clazz.getDeclaredField("name");
        field.setAccessible(true);
        MethodInfo fieldMethodInfo = field.getAnnotation(MethodInfo.class);
        if (fieldMethodInfo != null) {
            String val = fieldMethodInfo.value();
            System.out.println(val);
            assertEquals("name-info", val);
        }
        // 构造器上的注解
        Constructor<?> constructor = clazz.getConstructor();
        MethodInfo csMethodInfo = constructor.getAnnotation(MethodInfo.class);
        if (csMethodInfo != null) {
            String val = csMethodInfo.value();
            System.out.println(val);
            assertEquals("constructor-info", val);
        }
        // 方法上的注解
        Method method = clazz.getMethod("getName");
        MethodInfo mtMethodInfo = method.getAnnotation(MethodInfo.class);
        if (mtMethodInfo != null) {
            String val = mtMethodInfo.value();
            System.out.println(val);
            assertEquals("get-name-info", val);
        }
    }

    // 静态内部类
    // 在类上的注解
    @MethodInfo("App-info")
    public static class App {
        @MethodInfo("name-info")
        private String name;

        @MethodInfo("constructor-info")
        public App() {

        }

        @MethodInfo("get-name-info")
        public String getName() {
            return name;
        }

    }

    @Test
    public void strEscapeTest() {

        String hello = "<h1>Hello World!</h1>";
        String helloHtml = StringEscapeUtils.escapeHtml4(hello);
        String helloXml = StringEscapeUtils.escapeXml10(hello);
        String helloJson = StringEscapeUtils.escapeJson(hello);

        assertThat("&lt;h1&gt;Hello World!&lt;/h1&gt;", equalTo(helloHtml));

        assertThat("&lt;h1&gt;Hello World!&lt;/h1&gt;", equalTo(helloXml));
        assertThat("<h1>Hello World!<\\/h1>", equalTo(helloJson));

    }
}
