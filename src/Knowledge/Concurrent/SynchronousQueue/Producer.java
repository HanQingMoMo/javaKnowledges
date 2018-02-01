package Knowledge.Concurrent.SynchronousQueue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * @author hw 2017.1.16
 * 生产者线程
 */
public class Producer implements Runnable {

  private BlockingQueue<String> queue;

  public Producer(BlockingQueue<String> queue) {
    this.queue = queue;
  }

  @Override
  public void run() {
    while(true) {
      Random random = new Random();
      int ms = random.nextInt(2000);
      String msg = new String("x").concat(Integer.toString(random.nextInt(10)));
      try {
        queue.put(msg);
        System.out.println(Thread.currentThread().getName() + " : " + msg);
        Thread.sleep(ms);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
