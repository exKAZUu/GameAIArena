package jp.ac.waseda.cs.washi.gameaiarena.runner;

import java.io.Serializable;

public class IgnoringExceptionRunner<Arg, Result extends Serializable, Controller>
    extends AbstractRunner<Arg, Result, Controller> {

  private final AbstractRunner<Arg, Result, Controller> controller;

  public IgnoringExceptionRunner(AbstractRunner<Arg, Result, Controller> controller) {
    this.controller = controller;
  }

  @Override
  public Controller getComputerPlayer() {
    return controller.getComputerPlayer();
  }

  @Override
  public void runPreProcessing(Arg input) {
    controller.runPreProcessing(input);
  }

  @Override
  public void runProcessing() {
    try {
      controller.runProcessing();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public Result runPostProcessing() {
    return controller.runPostProcessing();
  }

  @Override
  public String toString() {
    return controller.toString();
  }
}
