package _07_Seven;

/**
 * Created with IntelliJ IDEA.
 * User: i33
 * Date: 13-7-16
 * Time: 上午10:15
 */
public class _75_CoinSide {

    /*
    这个程序的行为在1.4版和5.0版的Java平台上会有些变化。这个程序在这些版本上会分别做些什么呢？
    （如果你只能访问5.0版本的平台，那么你可以在编译的时候使用-source 1.4标记，以此来模拟1.4版
    的行为。）

            import java.util.Random;
            public class CoinSide {
            private static Random rnd = new Random();
                public static CoinSide flip() {
                    return rnd.nextBoolean() ?  Heads.INSTANCE : Tails.INSTANCE;
                }
                public static void main(String[ ] args) {
                    System.out.println(flip());
                }
            }

            class Heads extends CoinSide {
                private Heads() { }
                public static final Heads INSTANCE = new Heads();
                public String toString() {
                    return "heads";
                }
            }

            class Tails extends CoinSide {
                private Tails() { }
                public static final Tails INSTANCE = new Tails();
                public String toString() {
                    return "tails";
                }
            }

    该程序看起来根本没有使用5.0版的任何新特性，因此很难看出来为什么它们在行为上应该有差异。事实
    上，该程序在1.4或更早版本的平台上是不能编译的：

            CoinSide.java:7:
            incompatible types for ?: neither is a subtype of the other
            second operand: Heads
            third operand : Tails
                          return rnd.nextBoolean() ?
                                                        ^

    条件操作符（？:）的行为在5.0版本之前是非常受限的[JLS2 15.25]。当第二个和第三个操作数是引用
    类型时，条件操作符要求它们其中的一个必须是另一个的子类型。Heads和Tails彼此都不是对方的子类
    型，所以这里就产生了一个错误。为了让这段代码能够编译，你可以将其中一个操作数转型为二者的公共
    超类：

            return rnd.nextBooleam() ? (CoinSide)Heads.INSTANCE : Tails.INSTANCE;

    在5.0或更新的版本中，Java语言显得更加宽大了，条件操作符在第二个和第三个操作数是引用类型时总
    是合法的。其结果类型是这两种类型的最小公共超类。公共超类总是存在的，因为Object是每一个对象类
    型的超类型。在实际使用中，这种变化的主要结果就是条件操作符做正确的事情的情况更多了，而给出编
    译期错误的情况更少了。对于我们当中的语言菜鸟来说，作用于引用类型的条件操作符的结果所具备的编
    译期类型与在第二个和第三个操作数上调用下面的方法的结果相同：

             T choose(T a,T b) { }

    本谜题所展示的问题在1.4和更早的版本中发生得相当频繁，迫使你必须插入只是为了遮掩你的代码的真实
    目的而进行的转型。这就是说，该谜题本身是人为制造的。在5.0版本之前，使用类型安全的枚举模式来编
    写CoinSide对程序员来说会显得更自然一些[EJ Item 21]：

            import java.util.Random;
            public class CoinSide {
                 public static final CoinSide HEADS = new CoinSide("heads");
                 public static final CoinSide TAILS = new CoinSide("tails");
                 private final String name;

                 private CoinSide(String name) {
                      this.name = name;
                 }

                 public String toString() {
                      return name;
                 }

                 private static Random rnd = new Random();

                 public static CoinSide flip() {
                      return rnd.nextBoolean() ? HEADS : TAILS;
                 }

                 public static void main(String[] args) {
                      System.out.println(flip());
                 }
            }

    在5.0或更新的版本中，自然会将CoinSide当作是一个枚举类型来编写：

            public enum CoinSide {
                HEADS, TAILS;
                public String toString() {
                    return name().toLowerCase();
                }
                // flip 和 main 与上面的1.4版上的实现一样
            }

    本谜题的教训是：应该升级到最新的Java平台版本上。较新的版本都包含许多让程序员更轻松的改进，你并
    不需要费力去学习怎样利用所有的新特性，有些新特性不需要你付出任何努力就可以给你带来实惠。对语言
    和类库的设计者来说，得到的教训是：不要让程序员去做那些语言或类库本可以帮他们做的事。


    */

}
