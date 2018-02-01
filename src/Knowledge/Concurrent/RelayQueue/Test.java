package Knowledge.Concurrent.RelayQueue;

import java.util.concurrent.*;

/**
 * @author hw
 * 延时队列
 */
public class Test {
  public static void main(String[] args) {
    Task t1 = new Task("hello", 1000000000L);
    Task t2 = new Task("hanwei", 3000000000L);
    Task t3 = new Task("nihao", 5000000000L);
    DelayQueue<Task> queue = new DelayQueue<>();

    queue.offer(t1);
    queue.offer(t2);
    queue.offer(t3);

    //启动消费线程
    ExecutorService exec = Executors.newFixedThreadPool(1);
    exec.execute(new Consumer(queue));
    exec.shutdown();
  }

}
