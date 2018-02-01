package Knowledge.Concurrent.CountDownLatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @author hw 2017.1.17
 * 学生类，动作为考试
 */
public class Student implements Runnable {

  private CountDownLatch beginSingal;
  private CountDownLatch endSignal;
  private int id;

  public Student(CountDownLatch beginSingal, CountDownLatch endSignal, int id) {
    this.beginSingal = beginSingal;
    this.endSignal = endSignal;
    this.id = id;
  }

  @Override
  public void run() {
    try {
      //等待开考信号
      beginSingal.await();

      Random random = new Random();
      int tmp = 30 + random.nextInt(50);
      int time = tmp <= 60 ? tmp : 60;

      Thread.sleep(time * 1000);

      //当前学生完成考试
      endSignal.countDown();
      System.out.println("Student " + id + " completed the test, used " + time + " s.");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
