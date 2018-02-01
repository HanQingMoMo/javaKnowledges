package Knowledge.Concurrent.CountDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author hw 2017.1.17
 * 数学考试：时长60分钟，考试开始后学生方可答题，交卷不能早于开考前30分钟。
 * 全部学生交卷或者到时结束考试
 */
public class MathTest {
  public static void main(String args[]) {
    //考试开始信号
    CountDownLatch beginSingal = new CountDownLatch(1);
    //考试结束信号,20名学生全部交卷
    CountDownLatch endSingal = new CountDownLatch(20);

    for (int i = 1; i <= 20; i++) {
      new Thread(new Student(beginSingal, endSingal, i)).start();
    }
    beginSingal.countDown();
    System.out.println("考试正式开始 ！");
    try {
      endSingal.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("考试结束");
  }
}
