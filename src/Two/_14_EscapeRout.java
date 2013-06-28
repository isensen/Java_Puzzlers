package Two;

/**
 * Created with IntelliJ IDEA.
 * User: i33
 * Date: 13-6-28
 * Time: 下午2:58
 */
public class _14_EscapeRout {

    public static void main(String[] args){
        // \u0022 是双引号的Unicode转义字符, 最终打印结果是:2
        System.out.println("a\u0022.length()+\u0022b".length());
        // a".length()+"b
        System.out.println("a\".length()+\"b");
        // 14
        System.out.println("a\".length()+\"b".length());
    }

    /*
    理解这个谜题的关键是要知道：Java对在字符串字面常量中的Unicode转义字符没有提供任何特殊处理。编译器在将程序解析成各种符号之前，
    先将Unicode转义字符转换成为它们所表示的字符[JLS 3.2]。因此，程序中的第一个Unicode转义字符将作为一个单字符字符串字面常量
    （"a"）的结束引号，而第二个Unicode转义字符将作为另一个单字符字符串字面常量（"b"）的开始引号。程序打印的是表达式
    "a".length()+"b".length()，即2。

    希望将两个双引号字符置于字符串字面常量的内部。使用Unicode转义字符你是不能实现这一点的，但是你可以使用转义字符序列来实现
    [JLS 3.10.6]。表示一个双引号的转义字符序列是一个反斜杠后面紧跟着一个双引号（\”）。如果将最初的程序中的Unicode转义字符用转义
    字符序列来替换，那么它将打印出所期望的16：

        System.out.println("a\".length()+\"b".length());

    许多字符都有相应的转义字符序列，包括单引号（\'）、换行（\n）、制表符（\t）和反斜线（\\）。你可以在字符字面常量和字符串字面常
    量中使用转义字符序列。

    实际上，你可以通过使用被称为八进制转义字符的特殊类型的转义字符序列，将任何ASCII字符置于一个字符串字面常量或一个字符字面常量
    中，但是最好是尽可能地使用普通的转义字符序列。

    普通的转义字符序列和八进制转义字符都比Unicode转义字符要好得多，因为与Unicode转义字符不同，转义字符序列是在程序被解析为各种
    符号之后被处理的。

    ASCII是字符集的最小公共特性集，它只有128个字符，但是Unicode有超过65,000个字符。一个Unicode转义字符可以被用来在只使用
    ASCII字符的程序中插入一个Unicode字符。一个Unicode转义字符精确地等价于它所表示的字符。

    总之，在字符串和字符字面常量中要优先选择的是转义字符序列，而不是Unicode转义字符。Unicode转义字符可能会因为它们在编译序列中
    被处理得过早而引起混乱。不要使用Unicode转义字符来表示ASCII字符。在字符串和字符字面常量中，应该使用转义字符序列；对于除这些
    字面常量之外的情况，应该直接将ASCII字符插入到源文件中。
    */


}
