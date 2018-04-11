package com.lrs.common.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * FutureTask示例
 * <p>
 * http://beautyboss.farbox.com/post/study/shen-ru-xue-xi-futuretask
 */
public class FutureTaskExample {

    public static void main(String[] args) throws Exception {
        FutureTask<Integer> futureTask = new FutureTask<>(new Task());

        new Thread(futureTask, "Task-thread").start();
        TimeUnit.SECONDS.sleep(1);
        System.out.printf("Thread %s is running!\n", Thread.currentThread().getName());

        // 判断任务是否完成，未完成，休眠等待
        if (!futureTask.isDone()) {
            System.out.println("Task is not done!");
            TimeUnit.SECONDS.sleep(2);
            // 获取任务结果，如果任务尚未完成，阻塞等待
            int res = futureTask.get();

            System.out.printf("Task's result is %d \n", res);
        }
    }
}

class Task implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.printf("Thread %s is running!\n", Thread.currentThread().getName());
        int res = 0;
        for (int i = 0; i < 100; i++) {
            res += i;
        }
        TimeUnit.SECONDS.sleep(3);
        return res;
    }
}