package net.exkazuu.gameaiarena.manipulator;

import java.io.Serializable;

public class LimittingSumTimeManipulator<Arg, Result extends Serializable, Controller>
    extends Manipulator<Arg, Result, Controller> {

  private final Manipulator<Arg, Result, Controller> manipulator;
  private final int availableMillisecond;
  private int restExceededMillisecond;
  private Result result;

  public LimittingSumTimeManipulator(Manipulator<Arg, Result, Controller> manipulator,
      int availableMillisecond, int maxExceededMillisecond) {
    this.manipulator = manipulator;
    this.availableMillisecond = availableMillisecond;
    this.restExceededMillisecond = maxExceededMillisecond;
  }

  @Override
  public Controller getComputerPlayer() {
    return manipulator.getComputerPlayer();
  }

  @Override
  public void runPreProcessing(Arg input) {
    manipulator.runPreProcessing(input);
  }

  @SuppressWarnings("deprecation")
  @Override
  public void runProcessing() {
    if (restExceededMillisecond <= 0) {
      result = manipulator.runPostProcessing();
      return;
    }
    final Thread thread = new Thread(new Runnable() {
      @Override
      public void run() {
        manipulator.runProcessing();
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
      System.out.println("time was exceeded.");
      System.out.println("    consumed millseconds in this turn: " + consumedTime);
      System.out.println("    available millseconds in this turn: " + availableMillisecond);
      System.out.println("    all remaining available millseconds: " + restExceededMillisecond);
    }
    // 時間制限を超えた時点の結果を保存する
    result = manipulator.runPostProcessing();
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
    return manipulator.toString();
  }
}
