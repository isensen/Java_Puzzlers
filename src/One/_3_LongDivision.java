package One;

/**
 * Created with IntelliJ IDEA.
 * User: i33
 * Date: 13-6-18
 * Time: 下午4:59
 */
public class _3_LongDivision {

      public static void main(String[] args){
          //每天微秒数
          final long MICROS_PER_DAY = 24 * 60 * 60 * 1000 * 1000;
          //每天毫秒数
          final long MILLIS_PER_DAY = 24 * 60 * 60 * 1000;

          //除数和被除数都是long类型的，long类型大到了可以很容易地保存这两个乘积而不产生溢出。因此，看起来程序打印的必定是1000
          //500654080
          System.out.println(MICROS_PER_DAY);
          //5
          System.out.println(MICROS_PER_DAY / MILLIS_PER_DAY);

          //Reason:
          //问题在于常数MICROS_PER_DAY的计算“确实”溢出了。
          //尽管计算的结果适合放入long中，并且其空间还有富余，但是这个结果并不适合放入int中。
          //这个计算完全是以int运算来执行的，并且只有在运算完成之后，其结果才被提升到long,
          //而此时已经太迟了：计算已经溢出了，它返回的是一个小了200倍的数值。
          //从int提升到long是一种拓宽原始类型转换（widening primitive conversion），
          //它保留了（不正确的）数值。这个值之后被MILLIS_PER_DAY整除，
          //而MILLIS_PER_DAY的计算是正确的，因为它适合int运算。这样整除的结果就得到了5。

          //那么为什么计算会是以int运算来执行的呢？因为所有乘在一起的因子都是int数值。
          //当你将两个int数值相乘时，你将得到另一个int数值。Java不具有目标确定类型
          //的特性，这是一种语言特性，其含义是指存储结果的变量的类型会影响到计算所
          //使用的类型。

          final long MICROS_PER_DAY1 = 24L * 60 * 60 * 1000 * 1000;
          final long MILLIS_PER_DAY1 = 24L * 60 * 60 * 1000;
          //86400000000
          System.out.println(MICROS_PER_DAY1);
          //1000
          System.out.println(MICROS_PER_DAY1/MILLIS_PER_DAY1);

      }
}
