package net.exkazuu.gameaiarena.manipulator;

import java.io.Serializable;

public class LimittingTimeManipulator<Arg, Result extends Serializable>
    extends DefaultManipulator<Arg, Result> {

  private final int maxMillisecond;
  private boolean killed;

  public LimittingTimeManipulator(Manipulator<Arg, Result> manipulator, int maxMillisecond) {
    super(manipulator);
    this.maxMillisecond = maxMillisecond;
  }

  @Override
  protected void sendDataToAI(Arg input) {
    if (killed) {
      return;
    }
    manipulator.sendDataToAI(input);
  }

  @SuppressWarnings("deprecation")
  @Override
  protected void receiveDataFromAI() {
    if (killed) {
      return;
    }
    final Thread thread = new Thread(new Runnable() {
      @Override
      public void run() {
        manipulator.receiveDataFromAI();
      }
    });
    thread.start();
    try {
      thread.join(maxMillisecond);
    } catch (final InterruptedException e) {
      e.printStackTrace();
    }
    // 時間制限を超えた時点の結果を保存する
    if (thread.isAlive()) {
      System.err.println("Terminated the thread because time was exceeded.");
      thread.stop();
      release();
      killed = true;
    }
  }
}
