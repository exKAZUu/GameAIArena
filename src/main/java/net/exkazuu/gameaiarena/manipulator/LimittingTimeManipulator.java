package net.exkazuu.gameaiarena.manipulator;

import java.io.Serializable;

public class LimittingTimeManipulator<Arg, Result extends Serializable, Controller>
    extends Manipulator<Arg, Result, Controller> {

  private final Manipulator<Arg, Result, Controller> manipulator;
  private final int maxMillisecond;
  private Result result;

  public LimittingTimeManipulator(Manipulator<Arg, Result, Controller> manipulator,
      int maxMillisecond) {
    this.manipulator = manipulator;
    this.maxMillisecond = maxMillisecond;
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
    final Thread thread = new Thread(new Runnable() {
      @Override
      public void run() {
        manipulator.runProcessing();
      }
    });
    thread.start();
    try {
      thread.join(maxMillisecond);
    } catch (final InterruptedException e) {
      e.printStackTrace();
    }
    // 時間制限を超えた時点の結果を保存する
    result = manipulator.runPostProcessing();
    if (thread.isAlive()) {
      System.out.println("Terminated the thread because time was exceeded.");
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
