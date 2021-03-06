package _07_Seven;

/**
 * Created with IntelliJ IDEA.
 * User: i33
 * Date: 13-7-16
 * Time: 上午9:38
 */
public class _73_ApiClient {

    /*
    私有成员，即私有方法、域和类型这些概念的幕后思想是它们只是实现细节：一个类的实现者可以随意地添加一个新
    的私有成员，或者修改和移除一个旧的私有成员，而不需要担心对该类的客户造成任何损害。换句话说，私有成员被
    包含它们的类完全封装了。

    遗憾的是，在这种严密的盔甲保护中仍然存在细小的裂缝。例如，序列化就可以打破这种封装。如果使一个类成为可
    序列化的，并且接受缺省的序列化形式，那么该类的私有实例域将成为其导出API的一部分[EJ Item 54,55]。当客
    户正在使用现有的被序列化对象时，对私有表示的修改将会导致异常或者是错误的行为。

    但是编译期的错误又会怎么样呢？你能否写出一个final的“库”类和“客户”类，这两者都可以毫无问题地通过编译，
    然后在库类中添加一个私有成员，使得库类仍然能够编译，而客户类却再也不能编译了？

    如果你的解谜方案是要对库类添加一个私有构造器，以抑制通过缺省的公共构造器而创建实例的行为，那么你只是
    一知半解。本谜题要求你添加一个私有成员，严格地讲，构造器不是成员[JLS 6.4.3]。

    本谜题有数个解谜方案，其中一个是使用遮蔽：

            package library;
            public final class Api {
                 // private static class String{ }
                 public static String newString() {
                      return new String();
                 }
            }

            package client;
            import library.Api;
            public class Client {
                String s = Api.newString();
            }

    如上编写，该程序就可以毫无问题地通过编译。如果我们不注释掉library.Api中的局部类String的私有声明，那
    么Api.newString方法就再也不会返回java.lang.String类型了，因此变量Client.s的初始化将不能通过编译：

            client/Client.java:4: incompatible types
            found: library.Api.String, required: java.lang.String
                 String s = Api.newString();
                                              ^

    尽管我们所做的文本修改仅仅是添加了一个私有类声明，但是我们间接地修改了一个现有公共方法的返回类型，而
    这是一个不兼容的API修改，因为我们修改了一个被导出API所使用的名字的含义。

    这种解谜方案的数个变种也都可以实现这个目的。被遮蔽类型也可以来自一个外围类而不是来自java.lang；你可
    以遮蔽一个变量而不是一个类型，而被遮蔽变量可以来自一个static import声明或者是来自一个外围类。

    不修改类库的某个被导出成员的类型也可以解决本谜题。下面就是这样的一个解谜方案，它使用的是隐藏而不是遮蔽：

            package library;
            class ApiBase {
                 public static final int ANSWER = 42;
            }

            public final class Api extends ApiBase() {
                 // private static final int ANSWER = 6 * 9；
            }

            package client;
            import library.Api;
            public class Client {
                int answer = Api.ANSWER;
            }

    如上编写，该程序就可以毫无问题地通过编译。如果我们不注释掉library.Api中的私有声明，那么客户类将不能通过编译：

            client/Client.java:4: ANSWER has private access in library.Api
            int answer = Api.ANSWER;
                               ^

    这个新的私有域Api.ANSWER隐藏了公共域ApiBase.ANSWER，而这个域本来是应该被继承到Api中的。因为新的域被声明为是
    private的，所以它不能被Client访问。这种解谜方案的数个变种也都可以实现这个目的。你可以用隐藏一个实例域去替代隐
    藏一个静态域，或者用隐藏一个类型去替代隐藏一个域。

    你还可以用遮掩来解决本谜题。所有的解谜方案都是通过重用某个名字来破坏客户类。重用名字是危险的；应该避免隐藏、遮
    蔽和遮掩。是不是对此已经耳熟能详了？很好！


    */
}
