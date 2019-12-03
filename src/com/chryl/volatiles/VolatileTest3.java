package com.chryl.volatiles;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * volatiles:有序性
 * <p>
 * Created by Chryl on 2019/11/26.
 */
public class VolatileTest3 {

    static int x = 0, y = 0;
    //volatiles 避免指令重排序
//    static volatiles int x = 0, y = 0;

    //可能出现指令重排序,可能出现 a=1,b=1
    public static void main(String[] args) throws InterruptedException {
        Set<String> resSet = new HashSet<>();
        Map<String, Integer> resMap = new HashMap<>();
        for (int i = 0; i < 1000000; i++) {
            x = 0;
            y = 0;
            resMap.clear();
            Thread one = new Thread(new Runnable() {
                @Override
                public void run() {
                    int a = y;//3
                    x = 1; //1
                    resMap.put("a", a);
                }
            });


            Thread other = new Thread(new Runnable() {
                @Override
                public void run() {
                    int b = x;//3
                    y = 1; //2
                    resMap.put("b", b);
                }
            });

            one.start();
            other.start();
            one.join();
            other.join();


            resSet.add("a=" + resMap.get("a") + ",b=" + resMap.get("b"));
            System.out.println(resSet);
        }
    }

}
