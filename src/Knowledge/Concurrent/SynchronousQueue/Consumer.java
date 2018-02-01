package Knowledge.Concurrent.SynchronousQueue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * @author hw 2017.1.16
 * 消费者线程
 */
public class Consumer implements Runnable {
  private BlockingQueue<String> queue;

  public Consumer(BlockingQueue<String> queue) {
    this.queue = queue;
  }

  @Override
  public void run() {
    while(true) {
      Random random = new Random();
      int ms = random.nextInt(1000) + 1000;
      try {
        String msg = queue.take();
        System.out.println(Thread.currentThread().getName() + " : " + msg);
        Thread.sleep(ms);
      } catch(InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
