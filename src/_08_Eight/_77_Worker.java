package _08_Eight;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created with IntelliJ IDEA.
 * User: i33
 * Date: 13-7-17
 * Time: 上午8:51
 */
public class _77_Worker extends Thread {

    private volatile boolean quittingTime = false;

    @Override
    public void run() {
        while (!quittingTime)
            pretendToWork();
        System.out.println("Beer is good");
    }

    private void pretendToWork() {
        try {
            Thread.sleep(300); // Sleeping on the job?
        } catch (InterruptedException ex) { }
    }
    // It's quitting time, wait for worker - Called by good boss
    synchronized void quit() throws InterruptedException {
        quittingTime = true;
        join();
    }
    // Rescind quitting time - Called by evil boss
    synchronized void keepWorking() {
        quittingTime = false;
    }

    public static void main(String[] args) throws InterruptedException {
        final _77_Worker worker = new _77_Worker();
        worker.start();
        Timer t = new Timer(true); // Daemon thread

        t.schedule(new TimerTask() {
            @Override
            public void run() { worker.keepWorking(); }
        }, 500);

        Thread.sleep(400);
        worker.quit();
    }

    /*
    上面的这段程序模拟了一个小车间。程序首先启动了一个工人线程，该线程在停止时间到来之前会一直工作
    （至少是假装在工作），然后程序安排了一个定时器任务（timer task）用来模拟一个恶毒的老板，他会
    试图阻止停止时间的到来。最后，主线程作为一个善良的老板会告诉工人停止时间到了，并且等待工人停止
    工作。那么这个程序会打印什么呢？

    想要探究这个程序到底做了什么的最好方法就是手动地模拟一下它的执行过程。下面是一个近似的时间轴，
    这些时间点的数值是相对于程序的开始时刻进行计算的：

    •	300ms：工人线程去检查易变的 quittingTime 域，看看停止时间是否已经到了。这个时候并没有
               到停止时间，所以工人线程会回去继续“工作”。
    •	400ms：作为善良的老板的主线程会去调用工人线程的 quit方法。主线程会获得工人线程实例上的
               锁（因为quit是一个同步化的方法），将quittingTime的值设为true，并且调用工人线
               程上的join方法。这个对join方法的调用并不会马上返回，而是会等待工人线程执行完毕。
    •	500ms：作为恶毒的老板定时器任务开始执行。它将试图调用工人线程的keepWorking方法，但是
               这个调用将会被阻塞，因为keepWorking是一个同步化的方法，而主线程当时正在执行工
               人线程上的另一个同步化方法（quit方法）。
    •	600ms：工人线程会再次检查停止时间是否已经到来。由于quittingTime域是易变的，那么工人
               线程肯定会看到新的值true，所以它会打印 Beer is good 并结束运行。这会让主线程
               对join方法的调用执行返回，随后主线程也结束了运行。而定时器线程是后台的，所以它
               也会随之结束运行，整个程序也就结束了。

    所以，我们会认为程序将运行不到1秒钟，打印 Beer is good ，然后正常的结束。但是当你尝试运行
    这个程序的时候，你会发现它没有打印任何东西，而是一直处于挂起状态（没有结束）。我们的分析哪
    里出错了呢？

    其实，并没有什么可以保证上述几个交叉的事件会按照上面的时间轴发生。无论是Timer类还是
    Thread.sleep方法，都不能保证具有实时（real-time）性。这就是说，由于这里计时的粒度太粗，
    所以上述几个事件很有可能会在时间轴上互有重叠地交替发生。100毫秒对于计算机来说是一段很长
    的时间。此外，这个程序被重复地挂起；看起来好像有什么其他的东西在工作着，事实上，确实是有
    这种东西。

    我们的分析存在着一个基本的错误。在500ms时，当作为恶毒老板的定时器任务运行时，根据时间轴的
    显示，它对keepWorking方法的调用会被阻塞，因为keepWorking是一个同步化的方法并且主线程正在
    同一个对象上执行着同步化方法quit(在Thread.join中等待着)。这些都是对的，keepWorking确实
    是一个同步化的方法，并且主线程确实正在同一个对象上执行着同步化的quit方法。即使如此，定时器
    线程仍然可以获得这个对象上的锁，并且执行keepWorking方法。这是如何发生的呢？

    问题的答案涉及到了Thread.join的实现。这部分内容在关于该方法的文档中（JDK文档）是找不到的，
    至少在迄今为止发布的文档中如此，也包括5.0版。在内部，Thread.join方法在表示正在被连接
    （join）的那个Thread实例上调用Object.wait方法。这样就在等待期间释放了该对象上的锁。在我
    们的程序中，这就使得作为恶毒老板的定时器线程能够堂而皇之的将quittingTime重新设置成false，
    尽管此时主线程正在执行同步化的quit方法。这样的结果是，工人线程永远不会看到停止时间的到来，
    它会永远运行下去。作为善良的老板的主线程也就永远不会从join方法中返回了。

    使这个程序产生了预料之外的行为的根本原因就是WorkerThread类的作者使用了实例上的锁来确保
    quit方法和keepWorking方法的互斥，但是这种用法与超类（Thread）内部对该锁的用法发生了冲
    突。这里的教训是：除非有关于某个类的详细说明作为保证，否则千万不要假设库中的这个类对它的
    实例或类上的锁会做（或者不会做）某些事情。对于库的任何调用都可能会产生对wait、notify、
    notifyAll方法或者某个同步化方法的调用。所有这些，都可能对应用级的代码产生影响。

    如果你需要获得某个锁的完全控制权，那么就要确定没有任何其他人能够访问到它。如果你的类扩展
    了库中的某个类，而这个库中的类可能使用了它的锁，或者如果某些不可信的人可能会获得对你的类
    的实例的访问权，那么请不要使用与这个类或它的实例自动关联的那些锁。取而代之的，你应该在一
    个私有的域中创建一个单独的锁对象。在5.0版本发布之前，用于这种锁对象的正确类型只有Object
    或者它的某个普通的子类。从5.0版本开始，java.util.concurrent.locks提供了2种可选方案：
    ReentrantLock和ReentrantReadWriteLock。相对于Object类，这2个类提供了更好的机动性，
    但是它们使用起来也要更麻烦一点。它们不能被用在同步化的语句块（synchronized block）中，
    而且必须辅以try-finally语句对其进行显式的获取和释放。

    订正这个程序最直接的方法是添加一个Object类型的私有域作为锁，并且在quit和keepWorking
    方法中对这个锁对象进行同步。通过上述修改之后，该程序就会打印出我们所期望的Beer is good。
    可以看出，该程序能够产生正确行为并不依赖于它必须遵从我们前面分析的时间轴：

            private final Object lock = new Object();
            // It's quitting time, wait for worker - Called by good boss
            void quit() throws InterruptedException{
                synchronized (lock){
                    quittingTime = true;
                    join();
                }
            }
            // Rescind quitting time - Called by evil boss
            void keepWorking(){
                synchronized(lock){
                    quittingTime = false;
                }
            }

    另外一种可以修复这个程序的方法是让Worker类实现Runnable而不是扩展Thread，然后在创建每个
    工人线程的时候都使用Thread(Runnable)构造器。这样可以将每个Worker实例上的锁与其线程上的
    锁进行解耦。这是一个规模稍大一些的重构。

    正如库类对锁的使用会干扰应用程序一样，应用程序中对锁的使用也会干扰库类。例如，在迄今为止
    发布的所有版本的JDK（包括5.0版本）中，为了创建一个新的Thread实例，系统都会去获取Thread
    类上的锁。而执行下面的代码就可以阻止任何新线程的创建：

            synchronized(Thread.class){
                Thread.sleep(Long.MAX_VALUE);
            }

    总之，永远不要假设库类会（或者不会）对它的锁做某些事情。为了隔离你自己的程序与库类对锁的
    使用，除了那些专门设计用来被继承的库类之外，请避免继承其它库类 [EJ Item 15]。为了确保你
    的锁不会遭受外部的干扰，可以将它们设为私有以阻止其他人对它们的访问。

    对于语言设计者来说，需要考虑的是为每个对象都关联一个锁是否是合适的。如果你决定这么做了，
    就需要考虑限制对这些锁的访问。在Java中，锁实际上是对象的公共属性，或许它们变为私有的会
    更有意义。同时请记住在Java语言中，一个对象实际上就是一个锁：你在对象本身之上进行同步。
    如果每个对象都有一个锁，而且你可以通过调用一个访问器方法来获得它，这样或许会更有意义。

    */



}
