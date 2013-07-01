package _01_One;

/**
 * Created with IntelliJ IDEA.
 * User: i33
 * Date: 13-6-21
 * Time: 上午11:03
 */
public class _07_CleverSwap {

    public static void main(String[] args){



        //就像其名称所暗示的，这个程序应该交换变量x和y的值。如果你运行它，就会发现很悲惨，它失败了，打印的是
        //x = 0; y = 1984。
        int x = 1984; // (0x7c0)
        int y = 2001; // (0x7d1)
        x^= y^= x^= y;
        System.out.println("x= " + x + "; y= " + y);



        //重置一下,解析过程
        x = 1984;
        y = 2001;
        // Java中x^= y^= x^= y的实际行为
        int tmp1 = x ; // x在表达式中第一次出现
        int tmp2 = y ; // y的第一次出现
        int tmp3 = x ^ y ; // 计算x ^ y
        x = tmp3 ; // 最后一个赋值：存储x ^ y 到 x
        y = tmp2 ^ tmp3 ; // 第二个赋值：存储最初的x值到y中
        x = tmp1 ^ y ; // 第一个赋值：存储0到x中


        x = 1984;
        y = 2001;
        x = x ^ y;
        y = y ^ x;
        x = y ^ x;
        System.out.println("x= " + x + "; y= " + y);

        /*
          交换两个变量的最显而易见的方式是使用一个临时变量：
          int tmp = x;
          x = y;
          y = tmp;
          很久以前，当中央处理器只有少数寄存器时，人们发现可以通过利用异或操作符（^）的属性(x ^ y ^ x) == y来避免使用临时变量：
          x = x ^ y;
          y = y ^ x;
          x = y ^ x;
          这个惯用法曾经在C编程语言中被使用过，并进一步被构建到了C++中，但是它并不保证在二者中都可以正确运行。但是有一点是肯定的，
          那就是它在Java中肯定是不能正确运行的。


          Java语言规范描述到：操作符的操作数是从左向右求值的。为了求表达式 x ^= expr的值，x的值是在计算expr之前被提取的，并且这
          两个值的异或结果被赋给变量x。在CleverSwap程序中，变量x的值被提取了两次——每次在表达式中出现时都提取一次——但是两次提取
          都发生在所有的赋值操作之前。

          下面的代码段详细地描述了将互换惯用法分解开之后的行为，并且解释了为什么产生的是我们所看到的输出：

            // Java中x^= y^= x^= y的实际行为
            int tmp1 = x ; // x在表达式中第一次出现
            int tmp2 = y ; // y的第一次出现
            int tmp3 = x ^ y ; // 计算x ^ y
            x = tmp3 ; // 最后一个赋值：存储x ^ y 到 x
            y = tmp2 ^ tmp3 ; // 第二个赋值：存储最初的x值到y中
            x = tmp1 ^ y ; // 第一个赋值：存储0到x中

          在C和C++中，并没有指定表达式的计算顺序。当编译表达式x ^= expr时，许多C和C++编译器都是在计算expr之后才提取x的值的，这
          就使得上述的惯用法可以正常运转。尽管它可以正常运转，但是它仍然违背了C/C++有关不能在两个连续的序列点之间重复修改变量的
          规则。因此，这个惯用法的行为在C和C++中也没有明确定义。
          为了看重其价值，我们还是可以写出不用临时变量就可以互换两个变量内容的Java表达式的。但是它同样是丑陋而无用的：

            // 杀鸡用牛刀的做法，千万不要这么做！
            y = (x^= (y^= x))^ y ;

          这个教训很简单：在单个的表达式中不要对相同的变量赋值两次。表达式如果包含对相同变量的多次赋值，就会引起混乱，并且很少能
          够执行你希望的操作。即使对多个变量进行赋值也很容易出错。更一般地讲，要避免所谓聪明的编程技巧。它们都是易于产生bug的，
          很难以维护，并且运行速度经常是比它们所替代掉的简单直观的代码要慢。

          语言设计者可能会考虑禁止在一个表达式中对相同的变量多次赋值，但是在一般的情况下，强制执行这条禁令会因为别名机制的存在
          而显得很不灵活。例如，请考虑表达式 x = a[i]++ - a[j]++，它是否递增了相同的变量两次呢？这取决于在表达式被计算时i和j
          的值，并且编译器通常是无法确定这一点。

        */

    }

}