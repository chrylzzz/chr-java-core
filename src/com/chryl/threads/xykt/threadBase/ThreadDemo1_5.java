package com.chryl.threads.xykt.threadBase;

/**
 * 最好不要用
 * 自定义 修改标志位 ,最好不要用,防止 wait,take 等操作导致阻塞
 * <p>
 * Created by Chryl on 2019/8/22.
 */
public class ThreadDemo1_5 {


    private static class DemoThread2 extends Thread {

        /**
         * 自定义 修改标志位 ,最好不要用,防止 wait,take 等操作导致阻塞
         */
        //自定义标位置
        private volatile boolean cancel = false;

        //修改 标志位
        public void setCancel() {
            this.cancel = true;
        }

        public DemoThread2(String name) {
            super(name);
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            System.out.println(name + ": [isInterrupted] :" + isInterrupted());
            while (cancel) {

                try {
                    /**
                     *  sleep , 阻塞方法时,代码为苏醒时,不理会(无法感知)外界的任何以变操作,
                     *  此时进行setCancel() 时,虽然值改变,只能等阻塞方法苏醒之后,
                     *  才能监测到cancel的值(只有苏醒才能感知到cancel的值)
                     */
                    Thread.sleep(5000);  //阻塞  ,wait ,take
                } catch (InterruptedException e) {
                    System.out.println(name + ":" + isInterrupted());
                    /**
                     *出现中断异常,再手动调用中断:自行中断
                     */
//                    interrupt();

                    e.printStackTrace();
                }
                System.out.println(name + ": is running");
//                System.out.println(name + ": [isInterrupted] :" + isInterrupted());
            }
            System.out.println(name + ": [isInterrupted] :" + isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DemoThread2 demoThread2 = new DemoThread2("myThread");
        demoThread2.start();
        demoThread2.sleep(450);
        demoThread2.interrupt();

    }
}
