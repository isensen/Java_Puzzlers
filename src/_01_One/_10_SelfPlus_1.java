package _01_One;

/**
 * Created with IntelliJ IDEA.
 * User: i33
 * Date: 13-6-21
 * Time: 下午5:14
 */
public class _10_SelfPlus_1 {

    /*
    复合赋值操作符要求两个操作数都是原始类型的，例如int，或包装了的原始类型，例如Integer，
    但是有一个例外：如果在+=操作符左侧的操作数是String类型的，那么它允许右侧的操作数是任意
    类型，在这种情况下，该操作符执行的是字符串连接操作。简单赋值操作符（=）允许其左侧的是对
    象引用类型，这就显得要宽松许多了：你可以使用它们来表示任何你想要表示的内容，只要表达式的
    右侧与左侧的变量是赋值兼容的即可。
    */

    public static void main(String[] args){
        Object x = "Buy ";
        String i = "Effective Java!";
        //简单赋值是合法的，因为 x + i 是String类型的，而String类型又是与Object赋值兼容的：
        x = x + i;

        //复合赋值是非法的，因为左侧是一个Object引用类型，而右侧是一个String类型：
        //对语言设计者来说，加法的复合赋值操作符应该在右侧是String类型的情况下，
        //允许左侧是Object类型。这项修改将根除这个谜题所展示的违背直觉的行为。
        //x += i;
    }


}
