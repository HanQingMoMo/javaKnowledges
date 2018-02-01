package Knowledge.Concurrent.ArrayBlockingQueueTest;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author hw 2017.1.15
 * 消费者
 */
public class Consumer implements Runnable {
  private BlockingQueue queue;
  private Boolean flag = true;
  private volatile int sequence = 0;

  public Consumer(BlockingQueue queue) {
    this.queue = queue;
  }

  public void run() {
    while (flag) {
      try {
        Random random = new Random();
        int time = random.nextInt(30);
        Thread.sleep(time);
        String food = (String) queue.poll(1, TimeUnit.SECONDS);
        String name = Thread.currentThread().getName();
        if(food != null) {
          synchronized (this) {
            System.out.println(name + " : " + ++sequence + " -> " + food);
          }
        } else {
          flag = false;
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
