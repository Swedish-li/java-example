package com.lrs.common.hamcrest;

import org.junit.*;
import org.junit.runners.MethodSorters;

/**
 * 单元测试中的注解使用
 *
 * @author Swedish-li
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING) // 根据方法名的字典顺序
public class AnnotationTest {

    // 在执行整个类里的单元测试之前执行
    @BeforeClass
    public static void runBeforeClass() {
        System.out.println("@BeforeClass-runBeforeClass");
    }

    // 在执行完整个类里的单元测试之后执行
    @AfterClass
    public static void runAfterClass() {
        System.out.println("@AfterClass-runAfterClass");
    }

    @Test
    public void testFirst() {
        System.out.println("第一个执行！");
    }

    @Test
    public void testSecond() {
        System.out.println("第二个执行");
    }

    @Test
    public void testThird() {
        System.out.println("第三个执行");
    }

    @Ignore
    @Test
    public void ignoreTest() {
        System.out.println("这个测试方法会被忽略！");
    }

    // 在执行每个测试方法前执行
    @Before
    public void setUp() {
        System.out.println("@Before-setUp");
    }

    // 在执行每个测试方法后执行
    @After
    public void tearDown() {
        System.out.println("@After-tearDown");
    }

}
