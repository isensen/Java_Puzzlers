/**
 * Created with IntelliJ IDEA.
 * User: i33
 * Date: 13-6-19
 * Time: 上午11:13
 */
public class _6_Multicast {
    public static void main(String[] args){

        //连续使用了三个转型。那么它到底会打印出什么呢
        //65535
        System.out.println((int)(char)(byte) -1);

        /*
          1. int数值-1开始，然后从int转型为byte
             第一个转型将数值从32位窄化到了8位
          2. 第二个转型将数值从8位拓宽到了16位
          3. 最后一个转型又将数值从16位拓宽回了32位。
        */

        /*
          该程序的行为紧密依赖于转型的符号扩展行为。Java使用了基于2的补码的二进制运算，
          因此int类型的数值-1的所有32位都是置位的。从int到byte的转型是很简单的，它执
          行了一个窄化原始类型转化（narrowing primitive conversion），直接将除低8位
          外的所有位全部砍掉。这样做留下的是一个8位都被置位了的byte，它仍旧表示-1。
        */

    }
}
