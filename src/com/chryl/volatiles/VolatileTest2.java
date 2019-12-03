package com.chryl.volatiles;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatiles:无法保证原子性
 * <p>
 * Created by Chryl on 2019/11/26.
 */
public class VolatileTest2 {
    public static volatile int num = 0;
    public static AtomicInteger integer = new AtomicInteger(0);

    public static void increase() {
        num++;
        //或者用  原子数据类型
        integer.incrementAndGet();
    }
    //或者加  synchronized
//    public static synchronized void increase() {
//        num++;
//    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000; i++) {
                        increase();
                    }
                }
            });
            threads[i].start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println(integer);// integer = 10000
        System.out.println(num);// num <= 10000
        /**
         * 主要是因为volatile的内存模型决定.有时间差,必须知道内存模型图
         * 1.期望
         * cpu1把变量修改之后,写回内存,此时如果cpu2没有进行++操作,cpu2读取的num为写回内存之后的
         * 则cpu1->lock,cpu2++ 使数值不变.
         * 2.失误
         * 但是,cpu2在cpu1写回内存之前就已经++:导致cpu2读取的num的数值为cpu1写回内存之前的老数据,
         * 导致cpu2的++操作与cpu1的++操作冲突,使数值失效
         */
    }
}
