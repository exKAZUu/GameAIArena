package net.exkazuu.gameaiarena.manipulator;

import java.io.Serializable;

import net.exkazuu.gameaiarena.player.ExternalComputerPlayer;

public class LimittingSumTimeManipulator<Arg, Result extends Serializable>
    extends DefaultManipulator<Arg, Result> {

  private final int availableMillisecond;
  private int restExceededMillisecond;

  public LimittingSumTimeManipulator(Manipulator<Arg, Result> manipulator, int availableMillisecond,
      int maxExceededMillisecond) {
    super(manipulator);
    this.availableMillisecond = availableMillisecond;
    this.restExceededMillisecond = maxExceededMillisecond;
  }

  @Override
  protected void sendDataToAI(Arg input) {
    if (restExceededMillisecond <= 0) {
      return;
    }
    manipulator.sendDataToAI(input);
  }

  @Override
  protected void receiveDataFromAI() {
    if (restExceededMillisecond <= 0) {
      return;
    }

    Thread thread = new Thread(new Runnable() {
      @Override
      public void run() {
        manipulator.receiveDataFromAI();
      }
    });
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
      System.out.println("Time was exceeded.");
      System.out.println("    Consumed millseconds in this turn: " + consumedTime);
      System.out.println("    Available millseconds in this turn: " + availableMillisecond);
      System.out.println("    All remaining available millseconds: " + restExceededMillisecond);
    }
    // 時間制限を超えた時点の結果を保存する
    if (restExceededMillisecond <= 0 || thread.isAlive()) {
      System.out.println("Terminated the thread because time was exceeded.");
      thread.interrupt();
      release();
      restExceededMillisecond = 0;
    }
  }
}
