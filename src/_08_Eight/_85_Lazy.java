package _08_Eight;

/**
 * Created with IntelliJ IDEA.
 * User: i33
 * Date: 13-7-18
 * Time: 上午9:58
 */
public class _85_Lazy {

    // 下面这个可怜的小类实在是太懒了，甚至于都不愿意用通常的方法进行初始化，所以它求助于后台线程。
    // 这个程序会打印什么呢？每次你运行它的时候都会打印出相同的东西吗？
    private static boolean initialized = false;

    static {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                initialized = true;
            }
        });
        t.start();
        try{
            t.join();
        }catch (InterruptedException e){
            throw new AssertionError(e);
        }
    }

    public static void main(String[] args){
        System.out.println(initialized);
    }

    /*
    虽然有点奇怪，但是这个程序看起来很直观的。静态域initialized初始时被设为false。然后主线程创建
    了一个后台线程，该线程的run方法将initialized的值设为true。主线程启动了后台线程之后，就调用了
    join方法等待它的结束。当后台线程完成运行的时候，毫无疑问initialized的值已经被设为了true。当
    且仅当这个时候，调用了main方法的主线程会打印出initialized的值。如果是这样的话，程序肯定会打
    印出true吗？如果你运行该程序，你会发现它不会打印任何东西，它只是被挂起了。

    为了理解这个程序的行为，我们需要模拟它初始化的细节。当一个线程访问一个类的某个成员的时候，它
    会去检查这个类是否已经被初始化。在忽略严重错误的情况下，有4种可能的情况[JLS 12.4.2]：

            •	这个类尚未被初始化。
            •	这个类正在被当前线程初始化：这是对初始化的递归请求。
            •	这个类正在被其他线程而不是当前线程初始化。
            •	这个类已经被初始化。

    当主线程调用Lazy.main方法时，它会检查Lazy类是否已经被初始化。此时它并没有被初始化(情况1)，
    所以主线程会记录下当前正在进行初始化，并开始对这个类进行初始化。按照我们前面的分析，主线程会
    将initialized的值设为false，创建并启动一个后台线程，该线程的run方法会将initialized设为true，
    然后主线程会等待后台线程执行完毕。此时，有趣的事情开始了。

    那个后台线程调用了它的run方法。在该线程将Lazy.initialized设为true之前，它也会去检查Lazy类是
    否已经被初始化。这个时候，这个类正在被另外一个线程进行初始化（情况3）。在这种情况下，当前线程，
    也就是那个后台线程，会等待Class对象直到初始化完成。遗憾的是，那个正在进行初始化工作的线程，也
    就是主线程，正在等待着后台线程运行结束。因为这2个线程现在正相互等待着，该程序就死锁了(deadlock)。
    这就是所有的一切，真是遗憾。有2种方法可以订正这个程序。到目前为止，最好的方法就是不要在类进行
    初始化的时候启动任何后台线程：有些时候，2个线程并不比1个线程好。更一般的讲，要让类的初始化尽可能
    地简单。订正这个程序的第2种方法就是让主线程在等待后台线程之前就完成类的初始化：

            // Bad way to eliminate the deadlock. Complex and error prone
            public class Lazy {

                private static boolean initialized = false;

                private static Thread t = new Thread(new Runnable() {
                                public void run() {
                                        initialized = true;
                                }
                        });

                static {
                    t.start();
                }

                public static void main(String[] args){
                    try{
                        t.join();
                    }catch (InterruptedException e){
                        throw new AssertionError(e);
                    }
                    System.out.println(initialized);
                }
            }

    虽然这么做确实消除了死锁，但是它却是一个非常不好的想法。主线程需要等待后台线程完成工作，但是
    其他的线程不需要这么做。一旦主线程完成了对Lazy类的初始化，其他线程就可以使用这个类了。这使得
    在initialized的值还是false的时候，其他线程就可以观察到它。

    总之，在类的初始化期间等待某个后台线程很可能会造成死锁。要让类初始化的动作序列尽可能地简单。
    类的自动初始化被公认为是语言设计上的难题，Java的设计者们在这个方面做得很不错。如果你写了一
    些复杂的类初始化代码，很多种情况下，你这是在搬起石头砸自己的脚。

    */
}
