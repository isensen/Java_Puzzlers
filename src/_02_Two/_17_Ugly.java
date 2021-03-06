package _02_Two;

/**
 * Created with IntelliJ IDEA.
 * User: i33
 * Date: 13-7-1
 * Time: 上午11:16
 */
public class _17_Ugly {
    public static void main(String[] args){
        System.out.println("Hello w"+"orld");
    }

   /*
    \u0070\u0075\u0062\u006c\u0069\u0063\u0020\u0020\u0020\u0020
    \u0063\u006c\u0061\u0073\u0073\u0020\u0055\u0067\u006c\u0079
    \u007b\u0070\u0075\u0062\u006c\u0069\u0063\u0020\u0020\u0020
    \u0020\u0020\u0020\u0020\u0073\u0074\u0061\u0074\u0069\u0063
    \u0076\u006f\u0069\u0064\u0020\u006d\u0061\u0069\u006e\u0028
    \u0053\u0074\u0072\u0069\u006e\u0067\u005b\u005d\u0020\u0020
    \u0020\u0020\u0020\u0020\u0061\u0072\u0067\u0073\u0029\u007b
    \u0053\u0079\u0073\u0074\u0065\u006d\u002e\u006f\u0075\u0074
    \u002e\u0070\u0072\u0069\u006e\u0074\u006c\u006e\u0028\u0020
    \u0022\u0048\u0065\u006c\u006c\u006f\u0020\u0077\u0022\u002b
    \u0022\u006f\u0072\u006c\u0064\u0022\u0029\u003b\u007d\u007d

    这当然是一个合法的Java程序！这不是很显而易见吗？它会打印Hello World。噢，可能是不那么明显。事实上，该程序根本让人无法理解。
    每当你没必要地使用了一个Unicode转义字符时，都会使你的程序的可理解性更缺失一点，而该程序将这种做法发挥到了极致。如果你很好奇，
    可以看看下面给出的该程序在Unicode转义字符都被转换为它们所表示的字符之后的样子：
        public
        class Ugly
        {public
        static
        void main(
        String[]
        args){
        System.out
        .println(
        “Hello w”+
        “orld”);}}

    下面给出了将其进行格式化整理之后的样子：

        public class Ugly {
          public static void main(String[] args){
             System.out.println("Hello w"+"orld");
          }
        }

    Unicode转义字符只有在你要向程序中插入用其他任何方式都无法表示的字符时才是必需的，除此之外的任何情况都不应该避免使用它们。
    Unicode转义字符降低了程序的清晰度，并且增加了产生bug的可能性。

    对语言的设计者来说，也许使用Unicode转义字符来表示ASCII字符应该被定义为是非法的。这样就可以使得在谜题14、15和17（本谜题）
    中的程序非法，从而消除了大量的混乱。这个限制对程序员并不会造成任何困难。

   */

}
