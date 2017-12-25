package Knowledge.CompletableFuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * @author hw  2017.12.22
 * java异步
 */
public class Test {

  private static int tmp;

  //当上一阶段执行完成时，执行thenApply，返回CompletableFuture
  public static void thenApply() {

    CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {

      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      Random random = new Random();
      int tmp = random.nextInt(10);

      return Integer.toString(tmp);
    }).thenApplyAsync(s -> {

      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return "then apply : " + s;
    });

    System.out.println(future.join());
  }

  //当上一阶段执行完成时，执行thenAccept
  public static void thenAccept() {
    CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {

      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return 10;

    }).thenAcceptAsync(s -> {
      tmp += s;
      System.out.println("then accept : " + tmp);
    });

    future.join();
  }

  //不关心上一个异步事件的执行结果
  public static void thenRun() {
    CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return "hanwei";
    }).thenRun(() -> {
      tmp += 100;
      System.out.println("then run : " + tmp);
    });

    future.join();
  }

  //处理两个异步事件,在两个异步事件都完成后执行一个BiFunction接口
  public static void thenCombine() {
    CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return "hello";
    }).thenCombine(CompletableFuture.supplyAsync(() -> {
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return "wei";
    }), (s1, s2) -> {
      return s1 + " " + s2;
    });

    System.out.println(future.join());
  }

  //在两个异步事件都完成后执行一个Runable接口
  public static void runAfterBoth() {
    CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return "hello";
    }).runAfterBoth(CompletableFuture.supplyAsync(() -> {
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return "wei";
    }), () -> {
      System.out.println("run after both.");
    });

    future.join();
  }

  //两个异步事件取最快的结果,并执行一个Function接口
  public static void applyToEither() {
    String result = CompletableFuture.supplyAsync(() -> {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return "hello";
    }).applyToEither(CompletableFuture.supplyAsync(() -> {
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return "wei";
    }), s -> {
      return "We apply to " + s;
    }).join();

    System.out.println(result);
  }

  //两个异步事件取最快的结果，并执行一个Consumer接口
  public static void acceptEither() {
    CompletableFuture.supplyAsync(() -> {
      return "hw";
    }).acceptEither(
            CompletableFuture.supplyAsync(() -> {
              try {
                Thread.sleep(2000);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              return "hanwei";
            }),
            System.out::println
    ).join();
  }

  //任意一个异步事件执行完之后执行一个Runable接口
  public static void runAfterEither() {
    CompletableFuture.supplyAsync(() -> {
      return "hw";
    }).runAfterEither(CompletableFuture.supplyAsync(() -> {
      return "hanwei";
    }), () -> {
      System.out.println("run after either");
    }).join();
  }

  //处理异步中的异常
  public static void exceptionally() {
    int result = CompletableFuture.supplyAsync(() -> {
      return 1 / 0;
    }).exceptionally(e -> {
      return 10;
    }).join();

    System.out.println(" " + result);
  }

  //当异步运行完成时，对结果进行记录,该记录不会影响最终结果
  public static void whenComplete() {
    CompletableFuture.supplyAsync(() -> {
      Random random = new Random();
      int i = random.nextInt(2);
      int result = 10 / i;
      return "hanwei";
    }).whenComplete((s, t) -> {
      if (t != null) {
        System.out.println("Exception occurs");
      } else {
        System.out.println(s);
      }
    }).join();
  }

  //运行完成时，对结果进行处理
  public static void handle() {
    String str = CompletableFuture.supplyAsync(() -> {
      Random random = new Random();
      int i = random.nextInt(2);
      int result = 10 / i;
      return "hanwei";
    }).handle((s, t) -> {
      if (t != null) {
        return "exception";
      } else {
        return s;
      }
    }).join();

    System.out.println(str);
  }

  public static void main(String[] args) {
    tmp = 10;
    thenApply();
    thenAccept();
    thenRun();
    thenCombine();
    runAfterBoth();
    applyToEither();
    acceptEither();
    runAfterEither();
    exceptionally();
    whenComplete();
    handle();
  }
}