package Knowledge.Concurrent.ArrayBlockingQueueTest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author hw 2018.1.15
 * 两个生产者，三个消费者
 * http://blog.csdn.net/defonds/article/details/44021605
 */
public class Test {
  public static void main(String[] args) {
    BlockingQueue queue = new ArrayBlockingQueue(100);

    Producer producer = new Producer(queue);

    Consumer consumer = new Consumer(queue);

    new Thread(producer, "P1").start();
    new Thread(producer, "P2").start();
    new Thread(consumer, "C1").start();
    new Thread(consumer, "C2").start();
    new Thread(consumer, "C3").start();
  }
}
