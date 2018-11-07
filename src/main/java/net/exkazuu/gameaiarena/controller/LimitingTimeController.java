package net.exkazuu.gameaiarena.controller;

import java.io.Serializable;

public class LimitingTimeController<Arg, Result extends Serializable>
    extends DefaultController<Arg, Result> {

  private final int maxMillisecond;
  private boolean killed;

  public LimitingTimeController(Controller<Arg, Result> controller, int maxMillisecond) {
    super(controller);
    this.maxMillisecond = maxMillisecond;
  }

  @Override
  protected void sendDataToAI(Arg input) {
    if (killed) {
      return;
    }
    controller.sendDataToAI(input);
  }

  @SuppressWarnings("deprecation")
  @Override
  protected void receiveDataFromAI(Arg input) {
    if (killed) {
      return;
    }
    final Thread thread = new Thread(new Runnable() {
      @Override
      public void run() {
        controller.receiveDataFromAI(input);
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
