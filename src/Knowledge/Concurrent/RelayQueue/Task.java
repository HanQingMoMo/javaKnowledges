package Knowledge.Concurrent.RelayQueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author hw 2017.1.15
 * 实现了Delayed接口的任务类,在到期后打印msg
 */
public class Task implements Delayed {
  //消息内容
  private String msg;
  //到期时间
  private long expire;

  public Task(String msg, long timeout) {
    this.expire = timeout + System.nanoTime();
    this.msg = msg;
  }

  public void tell() {
    System.out.println(msg);
  }

  //用来排序
  @Override
  public int compareTo(Delayed tmp) {
    Task task = (Task)tmp;

    return expire > task.expire ? 1 : (expire < task.expire ? -1 : 0);
  }

  //判断是否到期
  @Override
  public long getDelay(TimeUnit unit) {
    return unit.convert(expire - System.nanoTime(), TimeUnit.NANOSECONDS);
  }
}
