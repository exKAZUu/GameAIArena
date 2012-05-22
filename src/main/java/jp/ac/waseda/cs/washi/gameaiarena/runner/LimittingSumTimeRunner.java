package jp.ac.waseda.cs.washi.gameaiarena.runner;

import java.io.Serializable;

public class LimittingSumTimeRunner<Arg, Result extends Serializable, Controller>
    extends AbstractRunner<Arg, Result, Controller> {

  private final AbstractRunner<Arg, Result, Controller> controller;
  private final int availableMillisecond;
  private int restExceededMillisecond;
  private Result result;

  public LimittingSumTimeRunner(AbstractRunner<Arg, Result, Controller> controller,
      int availableMillisecond, int maxExceededMillisecond) {
    this.controller = controller;
    this.availableMillisecond = availableMillisecond;
    this.restExceededMillisecond = maxExceededMillisecond;
  }

  @Override
  public Controller getController() {
    return controller.getController();
  }

  @Override
  public void runPreProcessing(Arg input) {
    controller.runPreProcessing(input);
  }

  @SuppressWarnings("deprecation")
  @Override
  public void runProcessing() {
    if (restExceededMillisecond <= 0) {
      return;
    }
    final Thread thread = new Thread(new Runnable() {
      @Override
      public void run() {
        controller.runProcessing();
      }
    });
    long currentTimeMillis = System.currentTimeMillis();
    thread.start();
    try {
      thread.join(availableMillisecond + restExceededMillisecond);
    } catch (final InterruptedException e) {
      e.printStackTrace();
    }

    long consumedTime = System.currentTimeMillis() - currentTimeMillis;
    if (consumedTime > availableMillisecond) {
      restExceededMillisecond -= consumedTime - availableMillisecond;
    }
    // 時間制限を超えた時点の結果を保存する
    result = controller.runPostProcessing();
    if (thread.isAlive()) {
      System.out.println("terminated the thread because time was exceeded");
      restExceededMillisecond = 0;
      thread.stop();
    }
  }

  @Override
  public Result runPostProcessing() {
    return result;
  }

  @Override
  public String toString() {
    return controller.toString();
  }
}
