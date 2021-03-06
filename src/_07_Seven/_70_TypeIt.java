package _07_Seven;

/**
 * Created with IntelliJ IDEA.
 * User: i33
 * Date: 13-7-16
 * Time: 上午8:58
 */
public class _70_TypeIt {


    /*
            package hack;
            import click.CodeTalk;
            public class TypeIt {
                private static class ClickIt extends CodeTalk {
                    void printMessage() {
                        System.out.println("Hack");
                    }
                }

                public static void main(String[ ] args) {
                    ClickIt clickit = new ClickIt();
                    clickit.doIt();
                }
            }

            package click;
            public class CodeTalk {
                public void doIt() {
                    printMessage();
                }

                void printMessage() {
                    System.out.println("Click");
                }
            }

    注意: 上面代码中,两个类不在同一个包内. 而 CodeTalk里的printMessage() 没有修饰符,所以默认是包范围.

    本谜题看起来很直观。Hack.TypeIt的main方法对TypeIt.ClickIt类实例化，然后调用其doIt方法，该方法是从CodeTalk继承而来。
    接着，该方法调用printMessage方法，它在TypeIt.ClickIt中被声明为打印Hack。然而，如果你运行该程序，它打印的将是Click。
    怎么会这样呢？

    上面的分析做出了一个不正确的假设，即Hack.TypeIt.ClickIt.printMessage方法覆写了click.CodeTalk.printMessage方法。
    一个包内私有的方法不能被位于另一个包中的某个方法直接覆写[JLS 8.4.8]。在程序中的这两个twoMessage方法是无关的，它们
    仅仅是具有相同的名字而已。当程序在hack包内调用printMessage方法时，运行的是hack.TypeIt.ClickIt.printMessage方法。
    这个方法将打印Click，这也就解释了我们所观察到的行为。

    如果你想让hack.TypeIt.ClickIt中的printMessage方法覆写在Click.CodeTalk中的该方法，那么你必须在Click.CodeTalk中
    的该方法声明之前添加protected或public修饰符。要使该程序能够编译，你还必须在hack.TypeIt.ClickIt的覆写声明的前面添
    加一个修饰符，该修饰符与你在Click.CodeTalk的printMessage方法上放置的修饰符相比，所具备的限制性不能更多[JLS 8.4.8.3]。
    换句话说，两个printMessage方法可以都被声明为是public的，也可以都被声明为是protected的，或者，超类中的方法被声明为
    是protected，而子类中的方法被声明为是public的。无论你执行了上述三种修改中的任何一种，该程序都将打印Hack，从而表明
    确实发生了覆写。

    总之，包内私有的方法不能直接被包外的方法声明所覆写。尽管包内私有的访问权限和覆写结合到一起会导致某种混乱，但是Java
    当前的行为是允许使用包的，以支持比单个的类更大的抽象封装。包内私有的方法是它们所属包的实现细节，在包外重用它们的名
    字是不会对包内产生任何影响的。

    */
}
