package _08_Eight;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: i33
 * Date: 13-7-17
 * Time: 上午10:12
 */
public class _78_Reflector {

    //一个关于反射的简单应用。这个程序会打印出什么呢
    public static void main(String[] args) throws Exception {
        Set<String> s = new HashSet<String>();
        s.add("foo");
        Iterator it = s.iterator();
        Method m = it.getClass().getMethod("hasNext");
        System.out.println(m.invoke(it));
    }


    /*
    这个程序首先创建了一个只包含单个元素的集合(set)，获得了该集合上的迭代器，然后利用反射调用了迭代器的hasNext方法，
    最后打印出此该方法调用的结果。由于该迭代器尚未返回该集合中那个唯一的元素，hasNext方法应该返回true。然而，运行这
    个程序却得到了截然不同的结果：

            Exception in thread "main" java.lang.IllegalAccessException:
               Class Reflector can not access a member of class HashMap$HashIterator with modifiers "public"
                   at Reflection.ensureMemberAccess(Reflection.java:65)
                   at Method.invoke(Method.java:578)
                   at Reflector.main(Reflector.java:11)

    这是怎么发生的呢？正如这个异常所显示的，hasNext方法当然是公共的，所以它在任何地方都是可以被访问的。那么为什么这个基
    于反射的方法调用是非法的呢？这里的问题并不在于该方法的访问级别（access level），而在于该方法所在的类型的访问级别。
    这个类型所扮演的角色和一个普通方法调用中的限定类型（qualifying type）是相同的[JLS 13.1]。在这个程序中，该方法是从
    某个类中选择出来的，而这个类型是由从it.getClass方法返回的Class对象表示的。这是迭代器的动态类型（dynamic type），
    它恰好是私有的嵌套类(nested class) java.util.HashMap.KeyIterator。出现 IllegalAccessException 异常的原因就是
    这个类不是公共的，它来自另外一个包：访问位于其他包中的非公共类型的成员是不合法的[JLS 6.6.1]。无论是一般的访问还是通
    过反射的访问，上述的禁律都是有效的。下面这段没有使用反射的程序也违反了这条规则。

            package library;
            public class Api{
                static class PackagePrivate{}
                public static PackagePrivate member = new PackagePrivate();
            }

            package client;
            import library.Api;
            class Client{
                public static void main(String[] args){
                    System.out.println(Api.member.hashCode());
                }
            }
    尝试编译这段程序会得到如下的错误：

            Client.java:5: Object.hashCode() isn't defined in a public
            class or interface; can't be accessed from outside package
                  System.out.println(Api.member.hashCode());
                                                     ^

    这个错误与前面那个由含有反射的程序所产生的运行期错误具有相同的意义。Object类型和hashCode方法都是公共的。问题在于
    hashCode方法是通过一个限定类型调用的，但用户访问不到这个类型。该方法调用的限定类型是library.Api.PackagePrivate，
    这是一个位于其他包的非公共类型。

    这并不意味着Client就不能调用Api.member的hashCode方法。要做到这一点，只需要使用一个可访问的限定类型即可，在这里
    可以将Api.member转型成Object。经过这样的修改之后，Client类就可以顺利地编译和运行了：

        System.out.println(((Object)Api.member).hashCode());

    实际上，这个问题并不会在普通的非反射的访问中出现，因为API的编写者在他们的公共API中只会使用公共的类型。即使这个问
    题有可能发生，它也会以编译期错误的形式显现出来，所以比较容易修改。而使用反射的访问就不同了，object.getClass()
    .getMethod(“methodName”) 这种惯用法虽然很常见，但是却有问题的，它不应该被使用。就像我们在前面的程序中看到的那样，
    这种用法很容易在运行期产生一个 IllegalAccessException。

    在使用反射访问某个类型时，请使用表示某种可访问类型的Class对象。回到我们前面的那个程序，hasNext方法是声明在一个公
    共类型 java.util.Iterator 中的，所以它的类对象应该被用来进行反射访问。经过这样的修改后，这个Reflector程序就会打
    印出true：

         Method m = Iterator.class.getMethod("hasNext");

    你完全可以避免这一类的问题，你应该只有在实例化时才使用反射，而方法调用都通过使用接口进行[EJ Item 35]。这种使用反
    射的用法，可以将那些调用方法的类与那些实现这些方法的类隔离开，并且提供了更高程度的类型安全。这种用法在“服务提供者
    框架”（Service Provider Frameworks）中很常见。这种模式并不能解决反射访问中的所有问题，但是如果它可以解决你所遇
    到的问题，请务必使用它。

    总之，访问其他包中的非公共类型的成员是不合法的，即使这个成员同时也被声明为某个公共类型的公共成员也是如此。不论这个
    成员是否是通过反射被访问的，上述规则都是成立的。这个问题很有可能只在反射访问中才会出现。对于平台的设计者来说，这里
    的教训与谜题67中的一样，应该让错误症状尽可能清晰地显示出来。对于运行期的异常和编译期的提示都还有些东西需要改进。



    */

}
