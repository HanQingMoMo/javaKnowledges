package Knowledge.Concurrent.ArrayBlockingQueueTest;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * @author hw 2017.1.15
 * 生产者
 */
public class Producer implements Runnable {
  private BlockingQueue queue;
  private volatile int sequence = 0;

  public Producer(BlockingQueue queue) {
    this.queue = queue;
  }

  public void run() {
    try {
      while (getSequence() < 200) {
        Random random = new Random();
        int time = random.nextInt(20);
        queue.put("baozi");

        String name = Thread.currentThread().getName();
        System.out.println(name + " : " + setSequence());
        Thread.sleep(time);
      }

    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }


  synchronized int getSequence() {
    return sequence;
  }

  synchronized int setSequence() {
    sequence += 1;
    return sequence;
  }
}
