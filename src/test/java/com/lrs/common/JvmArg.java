package com.lrs.common;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.*;
import java.util.Arrays;
import java.util.List;

/**
 * Java 虚拟机参数
 * <p>
 * 在java代码中获取java虚拟机参数：http://blog.csdn.net/zp357252539/article/details/51779191
 * <p>
 * java命令被用来启动jvm,装载和执行已经编译好的java类
 * <p>
 * 系统命令：%JAVA_HOME%bin java -option -option 为java虚拟机参数 基本参数： java 扩展参数： java -X
 *
 * @author Swedish-li
 */
public class JvmArg {

    private final static Logger logger = LoggerFactory.getLogger(JvmArg.class);

    // -XshowSettings:all
    @Test
    public void testJvmArg() {
        System.out.println("这是一个单元测试！");
    }

    /**
     * 使用-cp或-classpath设置类的加载路径
     */
    @Test
    public void testClasspath() {
        // 虚拟机自带的jar和zip文件，优先级最高
        String bootstrapPath = System.getProperty("sun.boot.class.path");
        logger.info(bootstrapPath);
        // Extension,jre目录下lib/ext文件夹下的jar文件
        String extPath = System.getProperty("java.ext.dirs");
        logger.info(extPath);

        // Users classes
        // 搜索当前目录，环境变量中的CLASSPATH,-classpath后的参数
        String userPath = System.getProperty("java.class.path");
        logger.info(userPath);

    }

    // 使用 java.lang.management.*
    @Test
    public void getJvmArgs() {
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        // 获取内存使用情况(heap,non heap)
        MemoryUsage heapUsage = memoryMXBean.getHeapMemoryUsage();
        // 最大
        long maxHeap = heapUsage.getMax();
        // 初始化大小
        long initHeap = heapUsage.getInit();
        // 已经使用的内存
        long usedHeap = heapUsage.getUsed();
        // java虚拟机被保证可以使用的内存
        long committedHeap = heapUsage.getCommitted();

        logger.info(String.format(
                "\nMax heap size:%d\n" // 最大
                        + "init heap size:%d\n" // 初始化
                        + "used Heap Size:%d\n" // 已使用
                        + "committed Heap size:%d\n" // 保证可以使用
                        + "Heap Memory Useage:%s\n", // 输出模板
                maxHeap, initHeap, usedHeap, committedHeap, heapUsage));
        // 非堆内存
        logger.info("Non heap Memory Useage:{}", memoryMXBean.getNonHeapMemoryUsage());

        // 虚拟机启动参数 java -options
        List<String> args = ManagementFactory.getRuntimeMXBean().getInputArguments();

        for (String arg : args) {
            logger.info(arg);
        }

        // jvm运行时信息,内存信息，核心信息,最大内存
        Runtime runtime = Runtime.getRuntime();
        // 可用核心数
        int countProcessors = runtime.availableProcessors();
        logger.info("available Processors:{}", countProcessors);

        // 操作系统相关信息
        OperatingSystemMXBean osmb = ManagementFactory.getOperatingSystemMXBean();
        logger.info("Operate System info:{}", osmb.getName());

        // 获取线程状态
        ThreadMXBean tmxb = ManagementFactory.getThreadMXBean();
        logger.info("Thread Info:{}", tmxb.getAllThreadIds());

        // 编译器情况
        CompilationMXBean cmxb = ManagementFactory.getCompilationMXBean();
        logger.info("Compilation info{}", cmxb.getName());

        // 获取多个内存池的使用情况
        List<MemoryPoolMXBean> pools = ManagementFactory.getMemoryPoolMXBeans();
        for (MemoryPoolMXBean memoryPoolMXBean : pools) {
            logger.info("pool name:{},pool Manager Names:{}", memoryPoolMXBean.getName(),
                    memoryPoolMXBean.getMemoryManagerNames());
        }

        // 获取GC信息
        List<GarbageCollectorMXBean> gcCollectons = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean gcc : gcCollectons) {
            logger.info("GC Name：{},GC pool names:{}", gcc.getName(), Arrays.toString(gcc.getMemoryPoolNames()));
        }

        // Jvm运行时信息
        RuntimeMXBean run = ManagementFactory.getRuntimeMXBean();
        logger.info("version:{},lib path:{}", run.getVmVersion(), run.getLibraryPath());
    }

    /**
     * 常用扩展指令 -Xms<size> 设置初识Java堆大小 -Xmx<size> 设置最大java堆大小 -Xss<size>
     * 设置Java线程堆栈大小
     *
     * 显示Java相关设置，并继续 -XshowSettings:[all|vm|properties|locale]
     */

    /**
     * 常用基本命令 -ea 启动断言，在单元测试的时候会用到 -D<name>=<value> 设置系统属性，在使用Maven的时候经常会看到这种写法
     * -verbose[:class|gc|jni] 启动虚拟机的一些运行时输出(类加载信息，GC信息，native方法调用情况)
     * -XX:PermSize=<size> 设置永久区初始化大小 -XX:MaxPermSize=<size> 设置永久区最大大小
     *
     * -XX:NewSize=<size> 设置堆栈新生代初始化（最小）大小 -XX:MaxNewSize=<size> 设置堆栈新生的最大容量
     *
     * 老年带容量不可以直接设置 老年带容量=堆容量-新生代容量
     *
     */

    /**
     * 参考：http://www.cnblogs.com/mikevictor07/p/5024645.html -XX:+PrintGCDetails
     * #打印详细的GC信息
     *
     * [Times: user=0.00 sys=0.00, real=0.05 secs] user:GC需要各个CPU的总时间
     * sys:回收器自身行为所占的时间 real:本次GC所消耗的真正时间
     *
     * -verbose:gc #简略打印GC信息
     * ***************************************************************** [GC
     * (System.gc()) 22323K->640K(117760K), 0.0008403 secs] [Full GC
     * (System.gc()) 640K->532K(117760K), 0.0049525 secs]
     * *****************************************************************
     *
     * -Xloggc:gc.log #记录GC日志到文件gc.log中
     *
     * -XX:+HeapDumpOnOutOfMemoryError
     *
     *
     * -XX:+PrintGCDateStamps #打印GC发生的详细时间
     *
     * -XX:+PrintGCApplicationStoppedTime #输出GC造成应用暂停的时间
     *
     * -XX:+PrintHeapAtGC #打印GC时堆栈的详细信息
     */
}
