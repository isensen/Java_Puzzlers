package _05_Five;

/**
 * Created with IntelliJ IDEA.
 * User: i33
 * Date: 13-7-9
 * Time: 上午10:18
 */
public class _47_Counter {
        private static int count = 0;
        public static final synchronized void increment() {
            count++;
        }
        public static final synchronized int getCount() {
            return count;
        }
    }
