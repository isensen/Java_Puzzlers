package _02_Two;

/**
 * Created with IntelliJ IDEA.
 * User: i33
 * Date: 13-6-27
 * Time: 下午4:11
 */
public class _12_ABC {

    public static void main(String[] args){
        String letters = "ABC";
        char[] numbers = {'1', '2', '3'};
        System.out.println(letters + " easy as " + numbers);


        String letters2 = "ABC";
        Object numbers2 = new char[] { '1', '2', '3' };
        System.out.print(letters2 + " easy as ");
        //它调用的是println的Object重载版本，而不是char[]重载版本。所以也不能达到输出的目的r
        System.out.println(numbers2);

    }
        /*
        尽管char是一个整数类型，但是许多类库都对其进行了特殊处理，因为char数值通常表示的是字符而不是整数。例如，将一个char数值
        传递给println方法会打印出一个Unicode字符而不是它的数字代码。字符数组受到了相同的特殊处理：println的char[]重载版本会打
        印出数组所包含的所有字符，而String.valueOf和StringBuffer.append的char[]重载版本的行为也是类似的。

        然而，字符串连接操作符在这些方法中没有被定义。该操作符被定义为先对它的两个操作数执行字符串转换，然后将产生的两个字符串
        连接到一起。对包括数组在内的对象引用的字符串转换定义如下：
        如果引用为null，它将被转换成字符串"null"。否则，该转换的执行就像是不用任何参数调用该引用对象的toString方法一样；但是
        如果调用toString方法的结果是null，那么就用字符串"null"来代替。

        那么，在一个非空char数组上面调用toString方法会产生什么样的行为呢？数组是从Object那里继承的toString方法[JLS 10.7]，
        规范中描述到：“返回一个字符串，它包含了该对象所属类的名字，'@'符号，以及表示对象散列码的一个无符号十六进制整数”[Java-API]。
        有关Class.getName的规范描述到：在char[]类型的类对象上调用该方法的结果为字符串"[C"。将它们连接到一起就形成了在我们的程序
        中打印出来的那个丑陋的字符串。

        有两种方法可以订正这个程序。你可以在调用字符串连接操作之前，显式地将一个数组转换成一个字符串：

            System.out.println(letters + " easy as " +
                                   String.valueOf(numbers));

        或者，你可以将System.out.println调用分解为两个调用，以利用println的char[]重载版本：

            System.out.print(letters + " easy as ");
            System.out.println(numbers);

        总之，char数组不是字符串。要想将一个char数组转换成一个字符串，就要调用String.valueOf(char[])方法。某些类库中的方法提供
        了对char数组的类似字符串的支持，通常是提供一个Object版本的重载方法和一个char[]版本的重载方法，而之后后者才能产生我们想要
        的行为。
        */


}
