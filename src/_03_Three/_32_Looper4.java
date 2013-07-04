package _03_Three;

/**
 * Created with IntelliJ IDEA.
 * User: i33
 * Date: 13-7-4
 * Time: 上午9:54
 */
public class _32_Looper4 {
    public static void main(String[] args){

        //Java 5.0 以上,可以构成无限循环
        Integer i = new Integer(0);
        Integer j = new Integer(0);
        while (i <= j && j <= i && i != j) {}
    }

    /*
    请提供一个对i的声明，将下面的循环转变为一个无限循环：

            while (i <= j && j <= i && i != j) {}

    噢，不，不要再给我看起来不可能的循环了！如果i <= j 并且 j <= i，i不是肯定等于j吗？这一属性对实数肯定有效。事实上，
    它是如此地重要，以至于它有这样的定义：实数上的≤关系是反对称的。Java的<=操作符在5.0版之前是反对称的，但是这从5.0版
    之后就不再是了。

    直到5.0版之前，Java的数字比较操作符（<、<=、>和>=）要求它们的两个操作数都是原始数字类型的（byte、char、short、int、
    long、float和double）[JLS 15.20.1]。但是在5.0版中，规范作出了修改，新规范描述道：每一个操作数的类型必须可以转换成
    原始数字类型[JLS 15.20.1，5.1.8]。问题难就难在这里了。

    在5.0版中，自动包装（autoboxing）和自动反包装（auto-unboxing）被添加到了Java语言中。如果你对它们并不了解，请查看：
    http://java.sun.com/j2se/5.0/docs/guide/language/autoboxing.html [Boxing]。<=操作符在原始数字类型集上仍然是
    反对称的，但是现在它还被应用到了被包装的数字类型上。（被包装的数字类型有：Byte、Character、Short、Integer、Long、
    Float和Double。）<=操作符在这些类型的操作数上不是反对称的，因为Java的判等操作符（==和!=）在作用于对象引用时，执行
    的是引用ID的比较，而不是值的比较。

    让我们更具体一些，下面的声明赋予表达式(i <= j && j <= i && i != j)的值为true，从而将这个循环变成了一个无限循环：

            Integer i = new Integer(0);
            Integer j = new Integer(0);

    前两个子表达式（i <= j 和j <= i）在i和j上执行解包转换[JLS 5.1.8]，并且在数字上比较所产生的int数值。i和j都表示0，
    所以这两个子表达式都被计算为true。第三个子表达式（i != j）在对象引用i和j上执行标识比较，因为它们都初始化为一个新
    的Integer实例，因此，第三个子表达式同样也被计算为true，循环也就永远地环绕下去了。

    你可能会感到奇怪，为什么语言规范没有修改为：当判等操作符作用于被包装的数字类型时，它们执行的是值比较。答案很简单：
    兼容性。当一种语言被广泛使用之后，以违反现有规范的方式去改变现有程序的行为是让人无法接受的。下面的程序过去总是保证
    可以打印false，因此它必须继续保持此特征：

            public class ReferenceComparison {
                public static void main(String[] args) {
                    System.out.println(
                         new Integer(0) == new Integer(0));
                }
            }

    判等操作符在其两个操作数中只有一个是被包装的数字类型，而另一个是原始类型时，执行的确实是数值比较。因为这在5.0版之前
    是非法的，所有在这里没有任何兼容性的问题。让我们更具体一些，下面的程序在1.4版中是非法的，而在5.0版中将打印 true：

            public class ValueComparison {
                public static void main(String[] args) {
                    System.out.println(
                         new Integer(0) == 0);
                }
            }

    总之，当两个操作数都是被包装的数字类型时，数值比较操作符和判等操作符的行为存在着根本的差异：数值比较操作符执行的是值
    比较，而判等操作符执行的是引用标识的比较。

    对语言设计者来说，如果判等操作符一直执行的都是数值比较（谜题13），那么生活可能就要简单得多、快乐得多。也许真正的教训
    应该是：语言设计者应该拥有高质量的水晶球，以预测语言的未来，并且做出相应的设计决策。严肃一点地讲，语言设计者应该考虑
    语言可能会如何演化，并且应该努力去最小化在演化之路上的各种制约影响。
    */

}
