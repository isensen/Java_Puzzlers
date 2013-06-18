/**
 * Created with IntelliJ IDEA.
 * User: i33
 * Date: 13-6-17
 * Time: 下午9:36
 * To change this template use File | Settings | File Templates.
 */
public class _1_isOdd {

    /**
     * 四分之一的时间里返回的都是错误的答案
     * 因为在所有的 int 数值中，有一半都是负数，而 isOdd 方法对于对所有负奇数的判断都会失败。
     * 在任何负整数上调用该方法都回返回 false ，不管该整数是偶数还是奇数。
     * 这是 Java 对取余操作符（%）的定义所产生的后果。
     * 该操作符被定义为对于所有的 int 数值 a 和所有的非零 int 数值 b，都满足下面的恒等式：
     * (a / b) * b + (a % b) == a
     * 换句话说，如果你用b整除a，将商乘以b，然后加上余数，那么你就得到了最初的值 a 。
     * 该恒等式具有正确的含义，但是当与 Java 的截尾整数整除操作符相结合时，它就意味着：
     * 当取余操作返回一个非零的结果时，它与左操作数具有相同的正负符号。
     * @param i
     * @return
     */
    public static boolean isOdd0 (int i ){
        return  i % 2 == 1;
    }

    /**
     * 正确方法
     * @param i
     * @return
     */
    public static boolean isOdd1 (int i){
        return  i % 2 !=0;
    }

    /**
     * 效率更高的位操作实现
     * @param i
     * @return
     */
    public static boolean isOdd2 (int i){
        return (i & 1) !=0;
    }
    public static void main(String... args) {
        System.out.println(isOdd0(2));
        System.out.println(isOdd0(1));
        System.out.println(isOdd0(0));
        System.out.println(isOdd0(-1));
        System.out.println(isOdd0(-2));

        System.out.println(isOdd1(2));
        System.out.println(isOdd1(1));
        System.out.println(isOdd1(0));
        System.out.println(isOdd1(-1));
        System.out.println(isOdd1(-2));

        System.out.println(isOdd2(2));
        System.out.println(isOdd2(1));
        System.out.println(isOdd2(0));
        System.out.println(isOdd2(-1));
        System.out.println(isOdd2(-2));

    }
}
