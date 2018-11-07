package net.exkazuu.gameaiarena.controller;

import java.io.Serializable;

public class LimitingSumTimeController<Arg, Result extends Serializable>
    extends DefaultController<Arg, Result> {

  private final int availableMillisecond;
  private int restExceededMillisecond;

  public LimitingSumTimeController(Controller<Arg, Result> controller, int availableMillisecond,
                                   int maxExceededMillisecond) {
    super(controller);
    this.availableMillisecond = availableMillisecond;
    this.restExceededMillisecond = maxExceededMillisecond;
  }

  @Override
  protected void sendDataToAI(Arg input) {
    if (restExceededMillisecond <= 0) {
      return;
    }
    controller.sendDataToAI(input);
  }

  @SuppressWarnings("deprecation")
  @Override
  protected void receiveDataFromAI(Arg input) {
    if (restExceededMillisecond <= 0) {
      return;
    }
    Thread thread = new Thread(() -> controller.receiveDataFromAI(input));
    long currentTimeMillis = System.currentTimeMillis();
    thread.start();
    try {
      thread.join(availableMillisecond + restExceededMillisecond);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    long consumedTime = System.currentTimeMillis() - currentTimeMillis;
    if (consumedTime > availableMillisecond) {
      restExceededMillisecond -= consumedTime - availableMillisecond;
      System.err.println("Time was exceeded.");
      System.err.println("    Consumed millseconds in this turn: " + consumedTime);
      System.err.println("    Available millseconds in this turn: " + availableMillisecond);
      System.err.println("    All remaining available millseconds: " + restExceededMillisecond);
    }
    // 時間制限を超えた時点の結果を保存する
    if (restExceededMillisecond <= 0 || thread.isAlive()) {
      System.err.println("Terminated the thread because time was exceeded.");
      thread.stop();
      release();
      restExceededMillisecond = 0;
    }
  }
}
