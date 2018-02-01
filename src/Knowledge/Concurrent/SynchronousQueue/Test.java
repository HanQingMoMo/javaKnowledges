package Knowledge.Concurrent.SynchronousQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @author hw 2017.1.16
 * 同步队列，只能容纳单个元素
 */
public class Test {
  public static void main(String[] args) {
    BlockingQueue<String> queue = new SynchronousQueue<>();
    Producer producer = new Producer(queue);
    Consumer consumer = new Consumer(queue);

    new Thread(producer, "producer1").start();
    new Thread(producer, "producer2").start();
    new Thread(consumer, "consumer1").start();
  }
}
