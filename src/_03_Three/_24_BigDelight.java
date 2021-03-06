package _03_Three;

/**
 * Created with IntelliJ IDEA.
 * User: i33
 * Date: 13-7-1
 * Time: 下午5:21
 */
public class _24_BigDelight {

    //循环遍历byte数值，以查找某个特定值。
    public static void main(String[] args) {

        for (byte b = Byte.MIN_VALUE; b < Byte.MAX_VALUE; b++) {
            System.out.print(b);
            if (b == 0x90)
                break;
        }


        for (byte b = Byte.MIN_VALUE; b < Byte.MAX_VALUE; b++) {
            if (b == 0x90)
                System.out.print("Joy!");
        }
    }

    /*
    这个循环在除了Byte.MAX_VALUE之外所有的byte数值中进行迭代，以查找0x90。这个数值适合用byte表示，
    并且不等于Byte.MAX_VALUE，因此你可能会想这个循环在该迭代会找到它一次，并将打印出Joy!。但是，
    所见为虚。如果你运行该程序，就会发现它没有打印任何东西。怎么回事？

    简单地说，0x90是一个int常量，它超出了byte数值的范围。这与直觉是相悖的，因为0x90是一个两位的
    十六进制字面常量，每一个十六进制位都占据4个比特的位置，所以整个数值也只占据8个比特，即1个byte。
    问题在于byte是有符号类型。常量0x90是一个正的最高位被置位的8位int数值。合法的byte数值是从-128
    到+127，但是int常量0x90等于+144。

    拿一个byte与一个int进行的比较是一个混合类型比较（mixed-type comparison）。如果你把byte数值
    想象为苹果，把int数值想象成为桔子，那么该程序就是在拿苹果与桔子比较。请考虑表达式
    ((byte)0x90 == 0x90)，尽管外表看起来是成立的，但是它却等于false。

    为了比较byte数值(byte)0x90和int数值0x90，Java通过拓宽原始类型转换将byte提升为一个int[JLS 5.1.2]，
    然后比较这两个int数值。因为byte是一个有符号类型，所以这个转换执行的是符号扩展，将负的byte数值提升为
    了在数字上相等的int数值。在本例中，该转换将(byte)0x90提升为int数值-112，它不等于int数值0x90，即+144。

    由于系统总是强制地将一个操作数提升到与另一个操作数相匹配的类型，所以混合类型比较总是容易把人搞糊涂。
    这种转换是不可视的，而且可能不会产生你所期望的结果。有若干种方法可以避免混合类型比较。我们继续有关水
    果的比喻，你可以选择拿苹果与苹果比较，或者是拿桔子与桔子比较。你可以将int转型为byte，之后你就可以拿
    一个byte与另一个byte进行比较了：

        if (b == (byte)0x90)
            System.out.println("Joy!");

    或者，你可以用一个屏蔽码来消除符号扩展的影响，从而将byte转型为int，之后你就可以拿一个int与另一个int进行比较了：

        if ((b & 0xff) == 0x90)
            System.out.print("Joy!");

    上面的两个解决方案都可以正常运行，但是避免这类问题的最佳方法还是将常量值移出到循环的外面，并将其在一个常量声明
    中定义它。下面是我们对此作出的第一个尝试：

        private static final byte TARGET = 0x90;
        public static void main(String[] args) {
            for (byte b = Byte.MIN_VALUE; b <
                 Byte.MAX_VALUE; b++) {
                 if (b == TARGET)
                     System.out.print("Joy!");
            }
        }

    遗憾的是，它根本就通不过编译。常量声明有问题，编译器会告诉你问题所在：0x90对于byte类型来说不是一个有效的数值。如
    果你想下面这样订正该声明，那么程序将运行得非常好：

        private static final byte TARGET = (byte)0x90;

    总之，要避免混合类型比较，因为它们内在地容易引起混乱（谜题5）。为了帮助实现这个目标，请使用声明的常量替代“魔幻数字”。
    你已经了解了这确实是一个好主意：它说明了常量的含义，集中了常量的定义，并且根除了重复的定义。现在你知道它还可以强制
    你去为每一个常量赋予适合其用途的类型，从而消除了产生混合类型比较的一种根源。

    对语言设计的教训是byte数值的符号扩展是产生bug和混乱的一种常见根源。而用来抵销符号扩展效果所需的屏蔽机制会使得程序
    显得混乱无序，从而降低了程序的可读性。因此，byte类型应该是无符号的。还可以考虑为所有的原始类型提供定义字面常量的机
    制，这可以减少对易于产生错误的类型转换的需求（谜题27）。

    */

}
