package _05_Five;

/**
 * Created with IntelliJ IDEA.
 * User: i33
 * Date: 13-7-10
 * Time: 下午3:57
 */
public class _54_Null {

    public static void greet() {
        System.out.println("Hello world!");
    }
    public static void main(String[] args) {
        ((_54_Null) null).greet();
    }

    /*
    这个程序看起来似乎应该抛出NullPointerExceptioin异常，因为其main方法是在常量null上调用greet方法，
    而你是不可以在null上调用方法的，对吗？嗯，某些时候是可以的。如果你运次该程序，就会发现它打印出了
    “Hello World!”

    理解本谜题的关键是Null.greet是一个静态方法。正如你在谜题48中所看到的，在静态方法的调用中，使用表
    达式作为其限定符并非是一个好主意，而这也正是问题之所在。不仅表达式的值所引用的对象的运行期类型在确
    定哪一个方法将被调用时并不起任何作用，而且如果对象有标识的话，其标识也不起任何作用。在本例中，没有
    任何对象，但是这并不会造成任何区别。静态方法调用的限定表达式是可以计算的，但是它的值将被忽略。没有
    任何要求其值为非空的限制。

    要想消除该程序中的混乱，你可以用它的类作为限定符来调用greet方法：

            public static void main(String[] args) {
                 _54_Null.greet();
            }

    然而更好的方式是完全消除限定符：

            public static void main(String[] args) {
                 greet();
            }

    总之，本谜题的教训与谜题48的完全相同：要么用某种类型来限定静态方法调用，要么就压根不要限定它们。对语
    言设计者来说，应该不允许用表达式来污染静态方法调用的可能性存在，因为它们只会产生混乱。

     */
}
