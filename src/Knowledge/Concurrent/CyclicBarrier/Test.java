package Knowledge.Concurrent.CyclicBarrier;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author hw 2017.1.17
 * 利用CyclicMap求出二维数组中最小的数字
 */
public class Test {

  public static void main(String[] args) {
    int[][] matrix = init();
    int[][] matrix1 = init(matrix);
    //用来存储每行的最小值
    ConcurrentMap<Integer, Integer> map = new ConcurrentHashMap<>();

    //用来阻塞当前线程
    CountDownLatch latch = new CountDownLatch(1);

    CyclicBarrier barrier = new CyclicBarrier(matrix.length, new Action(map, latch, "matrix") );

    for(int i = 0; i < matrix.length; i++) {
      new Thread(new Worker(barrier, map, matrix[i], i), "WorkerThread " + i).start();
    }

    //reuse barrier, not like CountDownLatch
    try {
      Thread.sleep(3000);
      latch.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    min(matrix);
    //只有当上一个矩阵处理完毕才会往下执行
    map.clear();

    min(matrix1);
    for(int i = 0; i < matrix1.length; i++) {
      new Thread(new Worker(barrier, map, matrix1[i], i), "WorkerThread " + i).start();
    }

  }

  public static int[][] init() {
    Random random = new Random();
    int x = random.nextInt(5) + 2;
    int y = random.nextInt(1000) + 300;

    int[][] matrix = new int[x][y];

    for(int i = 0; i < x; i++) {
      for(int k = 0; k < y; k++) {
        matrix[i][k] = random.nextInt(100000000);
      }
    }

    return matrix;
  }

  public static int[][] init(int[][] matrix) {
    int x = matrix.length;
    int y = new Random().nextInt(1000) + 400;

    int[][] matrix2 = new int[x][y];

    for(int i = 0; i < x; i++) {
      for(int k = 0; k < y; k++) {
        matrix2[i][k] = new Random().nextInt(100000000);
      }
    }

    return matrix2;
  }
  /**
   * 工作线程用来计算每行的最小值
   */
  private static class Worker implements Runnable {
    private CyclicBarrier barrier;
    private int[] array;
    private int index;
    private ConcurrentMap<Integer, Integer> map;

    public Worker(CyclicBarrier barrier, ConcurrentMap<Integer, Integer> map, int[] array, int index) {
      this.barrier = barrier;
      this.map = map;
      this.array = array;
      this.index = index;
    }

    @Override
    public void run() {
      try {
        Thread.sleep(new Random().nextInt(5000) + 1000);
        int min = Arrays.stream(array).min().getAsInt();
        map.putIfAbsent(index, min);

        barrier.await();

        System.out.println(Thread.currentThread().getName() + " end of test! " + min);
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (BrokenBarrierException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * 计算出所有行的最小值后输出整个map的最小值
   */
  private static class Action implements Runnable {
    private ConcurrentMap<Integer, Integer> map;
    private CountDownLatch latch;
    private String name;

    public Action(ConcurrentMap<Integer, Integer> map, CountDownLatch latch, String name) {
      this.map = map;
      this.latch = latch;
      this.name = name;
    }

    @Override
    public void run() {
      int minimun = map.values().stream().min(Integer::compareTo).get();
      String thread = Thread.currentThread().getName();
      System.out.println(thread + " The minimum integer of " + name + " is " + minimun);
      latch.countDown();
    }
  }

  public static void min(int[][] matrix) {
    int x = matrix.length;
    int y = matrix[0].length;
    int min = 100000000;

    for(int i = 0; i < x ; i++) {
      for(int k = 0; k < y; k++) {
        if(matrix[i][k] < min) {
          min = matrix[i][k];
        }
      }
    }

    System.out.println("minimun of matrix is " + min);
  }

}