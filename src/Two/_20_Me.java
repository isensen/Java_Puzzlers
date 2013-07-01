package Two;

import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: i33
 * Date: 13-7-1
 * Time: 下午2:48
 */
public class _20_Me {

    public static void main(String[] args) {

        System.out.println(_20_Me.class.getName());

        System.out.println(_20_Me.class.getName().replaceAll(".", "/") + ".class");
        //第一个参数是正则表达式
        //正则表达式中, . 是代表所有字符
        // \.才代表是 . 本身,所以用 \\ 实现 一个反斜杠,然后再加上一个点,就是 \\.
        System.out.println(_20_Me.class.getName().replaceAll("\\.", "/") + ".class");

        System.out.println(Pattern.quote("."));
        System.out.println(_20_Me.class.getName().replaceAll(Pattern.quote("."),"/") + ".class");
    }

    /*
     该程序看起来会获得它的类名（“Two._20_Me”），然后用“/”替换掉所有出现的字符串“.”，并在末尾追加字符串“.class”。
     你可能会认为该程序将打印com/javapuzzlers/Me.class，该程序正式从这个类文件中被加载的。如果你运行这个程序，就会发现它
     实际上打印的是///////////////////.class。到底怎么回事？难道我们是斜杠的受害者吗？

     问题在于String.replaceAll接受了一个正则表达式作为它的第一个参数，而并非接受了一个字符序列字面常量。（正则表达式已经
     被添加到了Java平台的1.4版本中。）正则表达式“.”可以匹配任何单个的字符，因此，类名中的每一个字符都被替换成了一个斜杠，
     进而产生了我们看到的输出。

     要想只匹配句点符号，在正则表达式中的句点必须在其前面添加一个反斜杠（\）进行转义。因为反斜杠字符在字面含义的字符串中
     具有特殊的含义——它标识转义字符序列的开始——因此反斜杠自身必须用另一个反斜杠来转义，这样就可以产生一个转义字符序列，
     它可以在字面含义的字符串中生成一个反斜杠。把这些合在一起，就可以使下面的程序打印出我们所期望的com/javapuzzlers/Me.class：

         System.out.println(_20_Me.class.getName().replaceAll("\\.","/") + ".class");

     为了解决这类问题，5.0版本提供了新的静态方法java.util.regex.Pattern.quote。它接受一个字符串作为参数，并可以添加必
     需的转义字符，它将返回一个正则表达式字符串，该字符串将精确匹配输入的字符串。下面是使用该方法之后的程序：

        System.out.println(Me.class.getName().replaceAll(Pattern.quote("."),"/") + ".class");

     该程序的另一个问题是：其正确的行为是与平台相关的。并不是所有的文件系统都使用斜杠符号来分隔层次结构的文件名组成部分的。
     要想获取一个你正在运行的平台上的有效文件名，你应该使用正确的平台相关的分隔符号来代替斜杠符号。这正是下一个谜题所要做
     的。

    */
}
