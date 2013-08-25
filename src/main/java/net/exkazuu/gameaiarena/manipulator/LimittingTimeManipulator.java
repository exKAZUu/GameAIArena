package net.exkazuu.gameaiarena.manipulator;

import java.io.Serializable;

import net.exkazuu.gameaiarena.player.ExternalComputerPlayer;

public class LimittingTimeManipulator<Arg, Result extends Serializable>
    extends Manipulator<Arg, Result> {

  private final Manipulator<Arg, Result> manipulator;
  private final int maxMillisecond;
  private Result result;

  public LimittingTimeManipulator(Manipulator<Arg, Result> manipulator,
      int maxMillisecond) {
    this.manipulator = manipulator;
    this.maxMillisecond = maxMillisecond;
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
      ExternalComputerPlayer player = manipulator.getExternalComputerPlayer();
      if (player != null) {
        player.release();
      }
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
