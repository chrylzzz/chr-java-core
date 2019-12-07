package com.chryl.base.bigdecimals;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * bigDecimal详解:
 * https://www.zhihu.com/tardis/sogou/art/63340027
 * <p>
 * 总结:
 * 1,商业计算使用BigDecimal
 * 2,尽量使用参数类型为String的构造函数
 * 3,BigDecimal是不可变的,在进行每一步运算时，都会产生一个新的对象,所以在做加减乘除运算时千万保存操作后的值
 * <p>
 * Created by Chryl on 2019/12/4.
 */
public class BigDecimalDemo {
    /**
     * 1.BigDecimal构造方法
     * public BigDecimal(double val) 将double表示形式转换为BigDecimal (不建议使用)
     * public BigDecimal(int val) 将int表示转换成BigDecimal
     * public BigDecimal(String val) 将String表示形式转换成BigDecimal
     */
    public static void main(String[] args) {

        BigDecimal bDouble = new BigDecimal(2.3);
        System.out.println("bDouble =" + bDouble);
        /**
         * 结果:bDouble =2.29999999999999982236431605997495353221893310546875
         2.为什么会出现这个结果？
         JDK的描述: 参数类型为double的构造方法的结果有一定的不可预知性。有人可能认为写入newBigDecimal(0.1)所创建的BigDecimal正好等于0.1，但是它实际等于0.1000000000055555.
         如果使用String构造方法是完全可以预知的，写入newBigDecimal(“0.1")将创建一个BigDecimal,它正好等于预期0.1.
         当double必须用BigDecimal的源时,请使用Double.toString(double)转成String, 然后使用String构造方法，或使用BigDecimal的静态方法valueOf.
         */
        BigDecimal bDouble_1 = BigDecimal.valueOf(2.3);
        System.out.println("bDouble_1 =" + bDouble_1);
        BigDecimal bDouble_2 = new BigDecimal(Double.toString(2.3));
        System.out.println("bDouble_2 =" + bDouble_2);

        /**
         3.BigDecimal加减乘除运算
         public BigDecimal add(BigDecimal value);       //加法
         public BigDecimal subtract(BigDecimal value);   //减法
         public BigDecimal multiply(BigDecimal value);   //乘法
         public BigDecimal divide(BigDecimal value);    //除法
         */
        BigDecimal a = new BigDecimal("4.5");
        BigDecimal b = new BigDecimal("1.5");
        System.out.println("a + b = " + a.add(b));
        System.out.println("a - b = " + a.subtract(b));
        System.out.println("a * b = " + a.multiply(b));
        System.out.println("a / b = " + a.divide(b));


        /**
         * 4.使用BigDecimal还可以对小数点位数进行截取例如:
         setScale函数第一个参数保留几位小数，第二个参数是舍入模式，包含以下

         ROUND_CEILING    //向正无穷方向舍入
         ROUND_DOWN    //向零方向舍入
         ROUND_FLOOR    //向负无穷方向舍入
         ROUND_HALF_DOWN    //向（距离）最近的一边舍入，除非两边（的距离）是相等,如果是这样，向下舍入, 例如1.55 保留一位小数结果为1.5
         ROUND_HALF_EVEN    //向（距离）最近的一边舍入，除非两边（的距离）是相等,如果是这样，如果保留位数是奇数，使用ROUND_HALF_UP，如果是偶数，使用ROUND_HALF_DOWN
         ROUND_HALF_UP    //向（距离）最近的一边舍入，除非两边（的距离）是相等,如果是这样，向上舍入, 1.55保留一位小数结果为1.6
         ROUND_UNNECESSARY    //计算结果是精确的，不需要舍入模式
         ROUND_UP    //向远离0的方向舍入
         另外值得一提的是BigDecimal的加减乘除返回一个新的BigDecimal对象,BigDecimal是不可变的.

         */
        BigDecimal a2 = new BigDecimal("4.5635");
        BigDecimal b2 = new BigDecimal("-4.5635");
        BigDecimal c2 = new BigDecimal("4.5635");
        a2 = a2.setScale(3, RoundingMode.HALF_UP);
        b2 = b2.setScale(3, BigDecimal.ROUND_DOWN);
        System.out.println("a2 =" + a2);
        System.out.println("b2 =" + b2);


    }
}
