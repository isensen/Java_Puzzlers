package _08_Eight;

/**
 * Created with IntelliJ IDEA.
 * User: i33
 * Date: 13-7-17
 * Time: 下午2:32
 */
public class _79_Pet {
    public final String name;
    public final String food;
    public final String sound;

    public _79_Pet(String name, String food, String sound){
        this.name = name;
        this.food = food;
        this.sound = sound;
    }

    public void eat(){
        System.out.println(name + ": Mmmmm, " + food );
    }

    public void play(){
        System.out.println(name + ": " + sound + " " + sound);
    }

    public void sleep(){
        System.out.println(name + ": Zzzzzzz...");
    }

    public void live(){
        new Thread(){
            @Override
            public void run(){
                while(true){
                    eat();
                    play();
                    //sleep();这里直接显示有错误
                    _79_Pet.this.sleep();
                }
            }
        }.start();
    }

    public static void main(String[] args){
        new _79_Pet("Fido", "beef", "Woof").live();
    }


    /*
    上面的这个类模拟了一个家庭宠物的生活。main方法创建了一个Pet实例，用它来表示一只名叫Fido的狗，然后让它运行。
    虽然绝大部分的狗都在后院里奔跑（run），这只狗却是在后台运行（run）。那么，这个程序会打印出什么呢？

    main方法创建了一个用来表示Fido的Pet实例，并且调用了它的live方法。然后，live方法创建并且启动了一个线程，
    该线程反复的调用其外围（enclosing）的Pet实例的eat、play和sleep方法，就这么一直进行下去。这些方法都会
    打印单独的一行，所以你会想到这个程序会反复的打印以下的3行：

            Fido: Mmmmm, beef
            Fido: Woof Woof
            Fido: Zzzzzzz…

    但是如果你尝试运行这个程序，你会发现它甚至不能通过编译。而产生的编译错误信息没有什么用处：

            Pet.java:28: cannot find symbol
            symbol: method sleep()
                                     sleep();
                                     ^

    为什么编译器找不到那个符号呢？这个符号确实是白纸黑字地写在那里。与谜题74一样，这个问题的源自重载解析过程
    的细节。编译器会在包含有正确名称的方法的最内层范围内查找需要调用的方法[JLS 15.12.1]。在我们的程序中，对
    于对sleep方法的调用，这个最内层的范围就是包含有该调用的匿名类(anonymous class)，这个类继承了
    Thread.sleep(long)方法和Thread.sleep(long,int)方法，它们是该范围内唯一的名称为sleep的方法，但是由于
    它们都带有参数，所以都不适用于这里的调用。由于该方法调用的2个候选方法都不适用，所以编译器就打印出了错误
    信息。

    从Thread那里继承到匿名类中的2个sleep 方法遮蔽（shadow）[JLS 6.3.1]了我们想要调用的sleep方法。正如你
    在谜题71和谜题73中所看到的那样，你应该避免遮蔽。在这个谜题中的遮蔽是间接地无意识地发生的，这使得它更加
    “阴险”。

    订正这个程序的一个比较显而易见的方法，就是把Pet中的sleep方法的名字改成snooze, doze或者nap。订正该程序
    的另一个方法，是在方法调用的时候使用受限的(qualified) this结构来显式地为该类命名。此时的调用就变成了
     Pet.this.sleep() 。

    订正该程序的第三个方法，也是可以被证明是最好的方法，就是采纳谜题77的建议，使用Thread(Runnable)构造器
    来替代对Thread的继承。如果你这么做了，原有的问题将会消失，因为那个匿名类不会再继承Thread.sleep方法。

    程序经过少许的修改，就可以产生我们想要的输出了，当然这里的输出可能有点无聊：

            public void live(){
              new Thread(new Runnable(){
                  public void run(){
                       while(true){
                            eat();
                            play();
                            sleep();
                       }
                  }
              }).start();
            }

    总之，要小心无意间产生的遮蔽，并且要学会识别表明存在这种情况的编译器错误信息。对于编译器的编写者来说，你
    应该尽力去产生那些对程序员来说有意义的错误消息。例如在我们的程序中，编译器应该可以警告程序员，存在着适用
    于方法调用但却被遮蔽掉的方法。


    */


}
