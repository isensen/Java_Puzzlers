package One;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: i33
 * Date: 13-6-18
 * Time: 下午2:18
 * To change this template use File | Settings | File Templates.
 */
public class _2_decimal {

    public static void main(String args[]){

        //0.8999999999999999
        //问题在于1.1这个数字不能被精确表示成为一个double，因此它被表示成为最接近它的double值。
        // 该程序从2中减去的就是这个值。遗憾的是，这个计算的结果并不是最接近0.9的double值。
        // 表示结果的double值的最短表示就是你所看到的打印出来的那个可恶的数字。
        //更一般地说，问题在于并不是所有的小数都可以用二进制浮点数来精确表示的
        System.out.println(2.00 - 1.10);


        //拙劣的解决方案——仍旧是使用二进制浮点数
        //这条语句打印的是正确的结果，但是这并不表示它就是对底层问题的通用解决方案：
        //它使用的仍旧是二进制浮点数的double运算。
        //浮点运算在一个范围很广的值域上提供了很好的近似，但是它通常不能产生精确的结果。
        //二进制浮点对于货币计算是非常不适合的，因为它不可能将0.1——或者10的其它任何次负幂
        //精确表示为一个长度有限的二进制小数
        System.out.printf("%.2f%n",2.00 - 1.10);

        //其中一种解决方式
        System.out.println((200 - 110) + "cents");

        //解决该问题的另一种方式是使用执行精确小数运算的BigDecimal。
        //它还可以通过JDBC与SQL DECIMAL类型进 行互操作。
        //注意：
        //一定要用BigDecimal(String)构造器，而千万不要用BigDecimal(double)。
        //后一个构造器将用它的参数的“精确”值来创建一个实例：
        //new BigDecimal(.1)将返回一个表示0.100000000000000055511151231257827021181583404541015625的BigDecimal。
        //通过正确使用BigDecimal，程序就可以打印出我们所期望的结果0.90
        //这个版本并不是十分地完美，因为Java并没有为BigDecimal提供任何语言上的支持。
        //使用BigDecimal的计算很有可能比那些使用原始类型的计算要慢一些，对某些大量使用小数计算的程序来说，
        //这可能会成为问题，而对大多数程序来说，这显得一点也不重要。
        //总之， 在需要精确答案的地方，要避免使用float和double；对于货币计算，要使用int、long或BigDecimal。
        //对于语言设计者来说，应该考虑对小数运算提供语言支持。一种方式是提供对操作符重载的有限支持，
        //以使得运算符可以被塑造为能够对数值引用类型起作用，例如BigDecimal。
        //另一种方式是提供原始的小数类型，就像COBOL与PL/I所作的一样。

        System.out.println(new BigDecimal("2.00").subtract(new BigDecimal("1.10")));



    }

}
