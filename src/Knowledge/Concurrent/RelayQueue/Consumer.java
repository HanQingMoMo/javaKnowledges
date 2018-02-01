package Knowledge.Concurrent.RelayQueue;

import java.util.concurrent.DelayQueue;

/**
 * @author hw 2017.1.15
 * 消费者,不断从延时队列中取Task并进行处理
 */
public class Consumer implements Runnable{
  private DelayQueue<Task> queue;

  public Consumer(DelayQueue<Task> queue) {
    this.queue = queue;
  }

  @Override
  public void run() {

    while(true) {
      try {
        Task t = queue.take();
        t.tell();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

}
