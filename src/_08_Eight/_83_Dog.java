package _08_Eight;

/**
 * Created with IntelliJ IDEA.
 * User: i33
 * Date: 13-7-18
 * Time: 上午9:27
 */
public class _83_Dog extends Exception {

    //从前有一个人，他认为世上只有一只不寻常的狗，所以他写出了如下的类，将它作为一个单例（singleton）[Gamma95]：
    public static final _83_Dog  INSTANCE = new _83_Dog();

    private _83_Dog() {}

    public String toString(){
        return "Woof";
    }

    /*
    结果证明这个人的做法是错误的。你能够在这个类的外部不使用反射来创建出第2个Dog实例吗？

    这个类可能看起来像一个单例，但它并不是。问题在于，Dog扩展了Exception，而Exception实现了java.io.Serializable。
    这就意味着Dog是可序列化的（serializable），并且解序列（deserialization）会创建一个隐藏的构造器。正如下面的这
    段程序所演示的，如果你序列化了Dog.INSTANCE，然后对得到的字节序列（byte sequence）进行解序列，最后你就会得到另
    外一个Dog。该程序打印的是false，表示新的Dog实例和原来的那个实例是不同的，并且它还打印了Woof，说明新的Dog实例也
    具有相应的功能：

            import java.io.*;
            public class CopyDog{ // Not to be confused with copycat
                public static void main(String[] args){
                     Dog newDog = (Dog) deepCopy(Dog.INSTANCE);
                     System.out.println(newDog == Dog.INSTANCE);
                     System.out.println(newDog);
                }

                // This method is very slow and generally a bad idea!
                static public Object deepCopy(Object obj){
                    try{
                        ByteArrayOutputStream bos =  new ByteArrayOutputStream();
                        new ObjectOutputStream(bos).writeObject(obj);
                        ByteArrayInputStream bin = new ByteArrayInputStream(bos.toByteArray());
                        return new ObjectInputStream(bin).readObject();
                    } catch(Exception e) {
                        throw new IllegalArgumentException(e);
                    }
                }
            }

    要订正这个问题，可在Dog中添加一个readResolve方法，它可以将那个隐藏的构造器转变为一个隐藏的静态工厂（static factory），
    以返回原来那个的Dog [EJ Items 2,57]。在Dog中添加了这个方法之后，CopyDog将打印true而不是false，表示那个“复本”实际上
    就是原来的那个实例：

            private Object readResolve(){
                // Accept no substitues!
                return INSTANCE;
            }

    这个谜题的主要教训就是一个实现了Serializable的单例类，必须有一个readResolve方法，用以返回它的唯一的实例。一个次要的教
    训就是，有可能由于对一个实现了Serializable的类进行了扩展，或者由于实现了一个扩展自Serializable的接口，使得我们在无意
    中实现了Serializable。给平台设计者的教训是，隐藏的构造器，例如序列化中产生的那个，会让读者对程序行为的产生错觉。



    */

}
