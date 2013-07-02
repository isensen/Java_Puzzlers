package _03_Three;

/**
 * Created with IntelliJ IDEA.
 * User: i33
 * Date: 13-7-2
 * Time: 上午11:18
 */
public class _27_Shifty {

    public static void main(String[] args) {

        //基础移位操作测试
        //移位操作符右端的那个数值的低5位才有用，即 X<<y; 是指y的低5位才有用，即不能大于32。
        int x=140;
        //10001100
        System.out.println(Integer.toBinaryString(x));
        //140
        System.out.println(x);
        //34二进制是100010,int只有其低5位有效,也就是00010,所以相当于左移2位
        //1000110000
        System.out.println(Integer.toBinaryString(x<<34));
        //560
        System.out.println(x<<34);

        //若对char，byte或者short进行移位处理，那么在移位进行之前，它们会自动转换成一个int。
        byte a=127;
        //1111111
        System.out.println(Integer.toBinaryString(a));
        //127
        System.out.println(a);
        a<<=2;
        //11111111111111111111111111111100
        System.out.println(Integer.toBinaryString(a));
        //-4
        System.out.println(a);
        byte b=127;
        System.out.println(Integer.toBinaryString(b));
        System.out.println(b);
        System.out.println(Integer.toBinaryString(b<<2));
        System.out.println(b<<2);

        //谜题
        int i = 0;
        while (-1 << i != 0)
            i++;
        System.out.println(i);

    }


    /*
     与谜题26中的程序一样，下面的程序也包含了一个记录在终止前有多少次迭代的循环。与那个程序不同的是，
     这个程序使用的是左移操作符（<<）。你的任务照旧是要指出这个程序将打印什么。当你阅读这个程序时，
     请记住 Java 使用的是基于2的补码的二进制算术运算，因此-1在任何有符号的整数类型中（byte、short、
     int或long）的表示都是所有的位被置位：

     常量-1是所有32位都被置位的int数值（0xffffffff）。左移操作符将0移入到由移位所空出的右边的最低位，
     因此表达式（-1 << i）将i最右边的位设置为0，并保持其余的32 - i位为1。很明显，这个循环将完成32次
     迭代，因为-1 << i对任何小于32的i来说都不等于0。你可能期望终止条件测试在i等于32时返回false，从
     而使程序打印32，但是它打印的并不是32。实际上，它不会打印任何东西，而是进入了一个无限循环。

     问题在于（-1 << 32）等于-1而不是0，因为移位操作符只使用其右操作数的低5位作为移位长度。或者是低6
     位，如果其左操作数是一个long类数值[JLS 15.19]。

     这条规则作用于全部的三个移位操作符：<<、>>和>>>。移位长度总是介于0到31之间，如果左操作数是long
     类型的，则介于0到63之间。这个长度是对32取余的，如果左操作数是long类型的，则对64取余。如果试图
     对一个int数值移位32位，或者是对一个long数值移位64位，都只能返回这个数值自身的值。没有任何移位
     长度可以让一个int数值丢弃其所有的32位，或者是让一个long数值丢弃其所有的64位。

         public static void main(String[] args) {
            int distance = 0;
            for (int val = -1; val != 0; val <<= 1)
                distance++;
            System.out.println(distance);
        }

     这个订正过的程序说明了一条普遍的原则：如果可能的话，移位长度应该是常量。如果移位长度紧盯着你不放，
     那么你让其值超过31，或者如果左操作数是long类型的，让其值超过63的可能性就会大大降低。当然，你并
     不可能总是可以使用常量的移位长度。当你必须使用一个非常量的移位长度时，请确保你的程序可以应付这
     种容易产生问题的情况，或者压根就不会碰到这种情况。

     前面提到的移位操作符的行为还有另外一个令人震惊的结果。很多程序员都希望具有负的移位长度的右移操
     作符可以起到左移操作符的作用，反之亦然。但是情况并非如此。右移操作符总是起到右移的作用，而左移
     操作符也总是起到左移的作用。负的移位长度通过只保留低5位而剔除其他位的方式被转换成了正的移位长
     度——如果左操作数是long类型的，则保留低6位。因此，如果要将一个int数值左移，其移位长度为-1，那
     么移位的效果是它被左移了31位。

     总之，移位长度是对32取余的，或者如果左操作数是long类型的，则对64取余。因此，使用任何移位操作符
     和移位长度，都不可能将一个数值的所有位全部移走。同时，我们也不可能用右移操作符来执行左移操作，
     反之亦然。如果可能的话，请使用常量的移位长度，如果移位长度不能设为常量，那么就要千万当心。

     语言设计者可能应该考虑将移位长度限制在从0到以位为单位的类型尺寸的范围内，并且修改移位长度为类型
     尺寸时的语义，让其返回0。尽管这可以避免在本谜题中所展示的混乱情况，但是它可能会带来负面的执行结
     果，因为Java的移位操作符的语义正是许多处理器上的移位指令的语义。
    */
}
