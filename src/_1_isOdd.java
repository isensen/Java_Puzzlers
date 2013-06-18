/**
 * Created with IntelliJ IDEA.
 * User: i33
 * Date: 13-6-17
 * Time: 下午9:36
 * To change this template use File | Settings | File Templates.
 */
public class _1_isOdd {

    public static boolean isOdd0 (int i ){
        return  i % 2 == 1;
    }

    public static boolean isOdd1 (int i){
        return  i % 2 !=0;
    }
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
