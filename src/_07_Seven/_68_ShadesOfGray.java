package _07_Seven;

/**
 * Created with IntelliJ IDEA.
 * User: i33
 * Date: 13-7-15
 * Time: 上午10:53
 */
public class _68_ShadesOfGray {


    /*

    下面的程序在相同的范围内具有两个名字相同的声明，并且没有任何明显的方式可以在它们二者之间做选择。这个程序会打印Black吗？
    它会打印White吗？甚至，它是合法的吗？

            public class ShadesOfGray {
                public static void main(String[] args){
                    System.out.println(X.Y.Z);
                }
            }

            class X {
                static class Y {
                    static String Z = "Black";
                }
                static C Y = new C();
            }

            class C {
                String Z = "White";
            }

    没有任何显而易见的方法可以确定该程序应该打印Black还是White。编译器通常会拒绝模棱两可的程序，而这个程序看起来肯定是模棱
    两可的。因此，它似乎应该是非法的。如果你试着运行它，就会发现它是合法的，并且会打印出White。你怎样才能事先了解这一切呢？

    可以证明，在这样的上下文环境中，有一条规则决定着程序的行为，即当一个变量和一个类型具有相同的名字，并且它们位于相同的作
    用域时，变量名具有优先权[JLS 6.5.2]。变量名将遮掩（obscure）类型名[JLS 6.3.2]。相似地，变量名和类型名可以遮掩包名。
    这条规则真的是相当地晦涩，任何依赖于它的程序都极有可能使它的读者晕头转向。

    幸运的是，遵守标准的Java命名习惯的程序继续从来都不会遇上这个问题。类应该以一个大写字母开头，以MixedCase的形式书写；变
    量应该以一个小写字母开头，以mixedCase的形式书写；而常量应该以一个大写字母开头，以ALL_CAPS的方式书写。单个的大写字母只
    能用于类型参数，就像在泛型接口Map<K,V>中那样。包名应该以lower.case的方式命名[JLS 6.8]。

    为了避免常量名与类名的冲突，在类名中应该将首字母缩拼词当作普通的词处理[EJ Item 38]。例如，一个表示全局唯一标识符的类应
    该被命名为Uuid，而不是UUID，尽管其首字母缩拼词通常被写为UUID。（Java平台库就违反了这项建议，因为它具有UUID、URL和URI
    这样的类名。）为了避免变量名与包名的冲突，请不要使用顶层的包名或领域名作为变量的名字，特别是不要将一个变量命名为com、
    org、net、edu、java或javax。

    要想移除ShadesOfGray这个程序中的所有不明确性，只需以遵守命名习惯的方式对其重写即可。很明显，下面的程序将打印Black。作
    为一种附加的好处，当你大声朗读这个程序时，听起来还最初的那个程序是完全一样的。

            public class ShadesOfGray {
                public static void main(String[ ] args){
                    System.out.println(Ex.Why.Z);
                }
            }

            class Ex {
                static class Why {
                    static String Z = "Black";
                }
                static See y = new See();
            }

            class See {
                String Z = "White";
            }

    总之，应该遵守标准的命名习惯以避免不同的命名空间之间的冲突，还有一个原因就是如果你违反这些习惯，那么你的程序将让人难以
    辨认。同样，为了避免变量名与通用的顶层包名相冲突，请使用MixedCase风格的类名，即使其名字是首字母缩拼词也应如此。通过遵
    守这些规则，你就可以确保你的程序永远不会遮掩类名或包名。再次说明一下，这里列举的仍然是你应该在覆写之外的情况中避免名字
    重用的一个实例。对语言设计者来说，应该考虑去消除遮掩的可能性。C#是通过将域和嵌套类置于相同的命名空间来实现这一点的。

    */
}
