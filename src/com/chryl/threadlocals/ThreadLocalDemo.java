package com.chryl.threadlocals;

/**
 * threadLocal:保证共享变量在每个线程中创建副本,每个线程可以访问自己内部的副本变量,使每个线程互不影响,保证线程的安全性(每个线程是隔离的)
 *
 * Created by Chryl on 2019/12/3.
 */
public class ThreadLocalDemo {

    //
    private static Integer num = 0;
    //threadlocal:
    private static ThreadLocal<Integer> numLocal = new ThreadLocal<Integer>() {
        protected Integer initialValue() {
            return 0;
        }
    };

    public static void main(String[] args) {
        Thread[] threads = new Thread[10];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                num += 5;
                System.out.println(
                        Thread.currentThread().getName() + ":"
                                + num
                );
            }, "Thread-" + num);
        }

        for (int t = 0; t < threads.length; t++) {
            threads[t].start();
        }

    }
}
