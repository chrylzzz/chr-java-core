package com.chryl.threads.xykt.threadBase;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * Created by Chryl on 2019/12/3.
 */
public class OnlyMain {
    public static void main(String[] args) {
        //cpu核心数:逻辑,获得cpu逻辑核心数,并不是物理核心数
        int i = Runtime.getRuntime().availableProcessors();
        System.out.println(i);

        //java虚拟机线程管理
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        //DemoTest类下的所有线程,不需要取monitor和synchronize信息,仅仅取线程和线程堆栈信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("[" + threadInfo.getThreadId() + "]" + ":" + threadInfo.getThreadName());

        }

    }
}
