package com.chryl.volatiles;

/**
 * 并发编程三大特性:可见性,有序性,原子性
 * java内存模型和volatile:保证可见性和有序性,无法保证原子性
 * <p>
 * Created by Chryl on 2019/11/26.
 */
public class VolatileTest {
    //未加volatile ,第一个线程无法感知第二个线程把initFlag修改了
    //因为initFlag在公共的内存空间(主内存ram),单个线程把initFlag放到自己的线程内存空间后(线程内的工作内存),
    //作修改并不会被另一个线程所感知,加上volatile则可以把变量变为在多个线程的可见性
//    public static  boolean initFlag = false;
    //多线程对共享变量的可见性
    public static volatile boolean initFlag = false;

    /**
     * volatile深层分析:底层通过汇编的lock的指令
     * 1.变量的值一修改,立即通过总线写回主内存,触发总线MESI缓存一致性协议,
     * 通过cpu的总线嗅探机制把其他线程工作内存的变量置为无效,把主内存的变量lock(该锁的影响很小),直到主内存write完在unlock
     */


    public static void main(String[] args) throws InterruptedException {
        //第一个线程
        new Thread(() -> {
            System.out.println("wait ....");
            while (!initFlag) {

            }
            System.out.println("success....");
        }).start();
        Thread.sleep(2000);

        //第二个线程
        new Thread(() -> prepareData()).start();

    }

    public static void prepareData() {
        System.out.println("preparing data...");
        initFlag = true;
        System.out.println("preparing end...");
    }
}
