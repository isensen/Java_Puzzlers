package _02_Two;

/**
 * Created with IntelliJ IDEA.
 * User: i33
 * Date: 13-6-27
 * Time: 下午4:02
 */
public class _11_LastLaugh {

    public static void main(String[] args){
        System.out.println("H"+"a");//双引号

        // 'H'和'a'是字符型字面常量,因为这两个操作数都不是字符串类型的，所以 + 操作符执行的是加法而不是字符串连接。
        System.out.println('H'+'a');//单引号

        System.out.println("" + 'H' + 'a');

        System.out.println("2 + 2 = " + 2+2);

        StringBuffer sb = new StringBuffer();
        sb.append('H');
        sb.append('a');
        System.out.println(sb);

        /*
        最终结果: Ha169
        编译器在计算常量表达式'H'+'a'时，是通过我们熟知的拓宽原始类型转换将两个具有字符型数值的操作数（'H'和'a'）
        提升为int数值而实现的。从char到int的拓宽原始类型转换是将16位的char数值零扩展到32位的int。对于'H'，char
        数值是72，而对于'a'，char数值是97，因此表达式'H'+'a'等价于int常量72 + 97，或169。

        站在语言的立场上，若干个char和字符串的相似之处是虚幻的。语言所关心的是，char是一个无符号16位原始类型整数
        ——仅此而已。对类库来说就不尽如此了，类库包含了许多可以接受char参数，并将其作为Unicode字符处理的方法。

        那么你应该怎样将字符连接在一起呢？你可以使用这些类库。例如，你可以使用一个字符串缓冲区：

            StringBuffer sb = new StringBuffer();
            sb.append('H');
            sb.append('a');
            System.out.println(sb);

        还可以通过确保至少有一个操作数为字符串类型，来强制 + 操作符去执行一个字符串连接操作，而不是一个加法操作。这种常见的惯用法用一个空字符串（""）作为一个连接序列的开始，如下所示：

	        System.out.println("" + 'H' + 'a');

        如果使用的是JDK 5.0，你还可以使用
	        System.out.printf("%c%c", 'H', 'a');

        */
    }


}
