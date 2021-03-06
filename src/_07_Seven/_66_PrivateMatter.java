package _07_Seven;

/**
 * Created with IntelliJ IDEA.
 * User: i33
 * Date: 13-7-15
 * Time: 上午10:26
 */
public class _66_PrivateMatter {

    /*
    在下面的程序中，子类的一个域具有与超类的一个域相同的名字。那么，这个程序会打印出什么呢？

            class Base {
                public String className = "Base";
            }

            class Derived extends Base {
                private String className = "Derived";
            }

            public class PrivateMatter {
                public static void main(String[ ] args) {
                    System.out.println(new Derived().className);
                }
            }

    对该程序的表面分析可能会认为它应该打印Derived，因为这正是存储在每一个Derived实例的className域中的内容。

    更深入一点的分析会认为Derived类不能编译，因为Derived中的className变量具有比Base中的className变量更具限制性的访问权限。

    如果你尝试着编译该程序，就会发现这种分析也不正确。该程序确实不能编译，但是错误却出在PrivateMatter中。

    如果className是一个实例方法，而不是一个实例域，那么Derived.className()将覆写Base.className()，而这样的程序是非法的。
    一个覆写方法的访问修饰符所提供的访问权限与被覆写方法的访问修饰符所提供的访问权限相比，至少要一样多[JLS 8.4.8.3]。

    因为className是一个域，所以Derived.className隐藏（hide）了Base.className，而不是覆盖了它[JLS 8.3]。对一个域来说，
    当它要隐藏另一个域时，如果隐藏域的访问修饰符提供的访问权限比被隐藏域的少，尽管这么做不可取的，但是它确实是合法的。事实上，
    对于隐藏域来说，如果它具有与被隐藏域完全无关的类型，也是合法的：即使Derived.className是GregorianCalendar类型的，
    Derived类也是合法的。

    在我们的程序中的编译错误出现在PrivateMatter类试图访问Derived.className的时候。尽管Base有一个公共域className，但是这
    个域没有被继承到Derived类中，因为它被Derived.className隐藏了。在Derived类内部，域名className引用的是私有域
    Derived.className。因为这个域被声明为是private的，所以它对于PrivateMatter来说是不可访问的。因此，编译器产生了类似下
    面这样的一条错误信息：

            PrivateMatter.java:11: className has private access in Derived
                    System.out.println(new Derived().className);
                                                           ^

    请注意，尽管在Derived实例中的公共域Base.className被隐藏了，但是我们还是可以通过将Derived实例转型为Base来访问到它。下面
    版本的PrivateMatter就可以打印出Base：

            public class PrivateMatter {
                public static void main(String[] args) {
                    System.out.println(((Base)new Derived()).className);
                }
            }

    这说明了覆写与隐藏之间的一个非常大的区别。一旦一个方法在子类中被覆写，你就不能在子类的实例上调用它了（除了在子类内部，通过
    使用super关键字来方法）。然而，你可以通过将子类实例转型为某个超类类型来访问到被隐藏的域，在这个超类中该域未被隐藏。

    如果你想让这个程序打印Derived，也就是说，你想展示覆写行为，那么你可以用公共方法来替代公共域。在任何情况下，这都是一个好主
    意，因为它提供了更好的封装[EJ Item 19]。下面的程序版本就使用了这项技术，并且能够打印出我们所期望的Derived：

            class Base {
                public String getClassName() {
                    return "Base";
                }
            }

            class Derived extends Base {
                public String getClassName() {
                    return "Derived";
                }
            }

            public class PrivateMatter {
                public static void main(String[] args) {
                    System.out.println(new Derived().getClassName());
                }
            }

    请注意，我们将Derived类中的getClassName方法声明成了public的，尽管在最初的程序中与其相对应的域是私有的。就像前面提到
    的那样，覆写方法的访问修饰符与它要覆写的方法的访问修饰符相比，所具有的限制性不能有任何降低。

    本谜题的教训是隐藏通常都不是一个好主意。Java语言允许你去隐藏变量、嵌套类型，甚至是静态方法（就像在谜题48所展示的那样），
    但是你不能认为你就应该去隐藏。隐藏的问题在于它将导致读者头脑的混乱。你正在使用一个被隐藏实体，或者是正在使用一个执行了
    隐藏的实体吗？要避免这类混乱，只需避免隐藏。

    如果一个类要隐藏一个域，而用来隐藏该域的域具有的可访问性比被隐藏域更具限制性，就像我们最初的程序那样，那么这就违反了
    包容性（subsumption）原则，即大家所熟知的Liskov置换原则（Liskov Substitution Principle）[Liskov87]。这项原则叙述
    道，你能够对基类所作的任何事，都同样能够作用于其子类。包容性是面向对象编程的自然心理模型的一个不可分割的部分。无论何时，
    只要违反了这项原则，就会对程序的理解造成困难。还有其它数种用另一个域来隐藏某个域的方法也会违反包容性：例如，两个域具有
    不同的类型；一个域是静态的而另一个域不是；一个域是final的而另一个域不是；一个域是常量而另一个域不是；以及两个域都是常
    量但是它们具有不同的值。

    对于语言设计者而言，应该考虑消除隐藏的可能性：例如，使所有的域都隐含地是私有的。如果这样做显得过于严苛，那么至少应该考
    虑对隐藏进行限制，以使其遵守包容性原则。

    总之，当你在声明一个域、一个静态方法或一个嵌套类型时，如果其名字与基类中相对应的某个可访问的域、方法或类型相同，就会发
    生隐藏。隐藏是容易产生混乱的：违反包容性的隐藏域在某种意义上是特别有害的。更一般地讲，除了覆写之外，要避免名字重用。



    */
}
