package _07_Seven;

/**
 * Created with IntelliJ IDEA.
 * User: i33
 * Date: 13-7-16
 * Time: 上午10:30
 */
public class Override_Hide_Overload_Shadow_Obscure {

    /*
    覆写(override):
    一个实例方法可以覆写（override）在其超类中可访问到的具有相同签名的所有实例方法[JLS 8.4.8.1]，从而使能了
    动态分派（dynamic dispatch）；换句话说，VM将基于实例的运行期类型来选择要调用的覆写方法[JLS 15.12.4.4]。
    覆写是面向对象编程技术的基础，并且是唯一没有被普遍劝阻的名字重用形式：

            class Base {
                 public void f() { }
            }

            class Derived extends Base {
                 public void f() { } // overrides Base.f()
            }




    隐藏(hide):
    一个域、静态方法或成员类型可以分别隐藏（hide）在其超类中可访问到的具有相同名字（对方法而言就是相同的方法
    签名）的所有域、静态方法或成员类型。隐藏一个成员将阻止其被继承[JLS 8.3, 8.4.8.2, 8.5]：

            class Base {
                 public static void f() { }
            }

            class Derived extends Base {
                 private static void f() { } // hides Base.f()
            }



    重载(overload):
    在某个类中的方法可以重载（overload）另一个方法，只要它们具有相同的名字和不同的签名。由调用所指定的重载方
    法是在编译期选定的[JLS 8.4.9, 15.12.2]：

            class CircuitBreaker {
                 public void f(int i)     { } // int overloading
                 public void f(String s) { } // String overloading
            }




    遮蔽(shadow):
    一个变量、方法或类型可以分别遮蔽（shadow）在一个闭合的文本范围内的具有相同名字的所有变量、方法或类型。如果
    一个实体被遮蔽了，那么你用它的简单名是无法引用到它的；根据实体的不同，有时你根本就无法引用到它[JLS 6.3.1]：

            class WhoKnows {
                 static String sentence = "I don't know.";
                 public static woid main(String[ ] args) {
                      String sentence = “I know!”;   // shadows static field
                      System.out.println(sentence);  // prints local variable
                 }
            }

    尽管遮蔽通常是被劝阻的，但是有一种通用的惯用法确实涉及遮蔽。构造器经常将来自其所在类的某个域名重用为一个参
    数，以传递这个命名域的值。这种惯用法并不是没有风险，但是大多数Java程序员都认为这种风格带来的实惠要超过其风
    险：

            class Belt {
                 private final int size;
                 public Belt(int size) { // Parameter shadows Belt.size
                      this.size = size;
                 }
            }



    遮掩(obscure):
    一个变量可以遮掩具有相同名字的一个类型，只要它们都在同一个范围内：如果这个名字被用于变量与类型都被许可的范
    围，那么它将引用到变量上。相似地，一个变量或一个类型可以遮掩一个包。遮掩是唯一一种两个名字位于不同的名字空
    间的名字重用形式，这些名字空间包括：变量、包、方法或类型。如果一个类型或一个包被遮掩了，那么你不能通过其简
    单名引用到它，除非是在这样一个上下文环境中，即语法只允许在其名字空间中出现一种名字。遵守命名习惯就可以极大
    地消除产生遮掩的可能性[JLS 6.3.2, 6.5]：

            public class Obscure {
                 static String System;  // Obscures type java.lang.System
                 public static void main(String[ ] args) {
                      // Next line won't compile: System refers to static field
                      System.out.println(“hello, obscure world!”);
                 }
            }


    */
}
