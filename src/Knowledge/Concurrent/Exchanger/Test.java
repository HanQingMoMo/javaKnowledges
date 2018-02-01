package Knowledge.Concurrent.Exchanger;

import java.util.Random;
import java.util.concurrent.Exchanger;

/**
 * @author hw 2018.2.1
 * swap object between two threads 线程间通信
 */
public class Test {

  private static class ExchangerRunnable implements Runnable {
    private Exchanger<String> exchanger = null;
    private String string = null;

    public ExchangerRunnable(Exchanger<String> exchanger, String string) {
      this.exchanger = exchanger;
      this.string = string;
    }

    @Override
    public void run() {
      String name = Thread.currentThread().getName();
      System.out.println(name + " : before exchange : " + string);
      try {
        Random random = new Random();
        int time = random.nextInt(1000) + 500;
        Thread.sleep(time);
        String result = exchanger.exchange(string);
        System.out.println(name + " : after exchange : " + result);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) {
    Exchanger<String> exchanger = new Exchanger<String>();
    ExchangerRunnable e1 = new ExchangerRunnable(exchanger, "hanwei");
    ExchangerRunnable e2 = new ExchangerRunnable(exchanger, "zhenbang");

    new Thread(e1, "hanwei").start();
    new Thread(e2, "zhenbang").start();
  }
}
