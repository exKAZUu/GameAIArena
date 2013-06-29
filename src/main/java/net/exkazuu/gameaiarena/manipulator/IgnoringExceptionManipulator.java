package net.exkazuu.gameaiarena.manipulator;

import java.io.Serializable;

public class IgnoringExceptionManipulator<Arg, Result extends Serializable, Controller>
    extends Manipulator<Arg, Result, Controller> {

  private final Manipulator<Arg, Result, Controller> manipulator;

  public IgnoringExceptionManipulator(Manipulator<Arg, Result, Controller> manipulator) {
    this.manipulator = manipulator;
  }

  @Override
  public Controller getComputerPlayer() {
    return manipulator.getComputerPlayer();
  }

  @Override
  public void runPreProcessing(Arg input) {
    manipulator.runPreProcessing(input);
  }

  @Override
  public void runProcessing() {
    try {
      manipulator.runProcessing();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public Result runPostProcessing() {
    return manipulator.runPostProcessing();
  }

  @Override
  public String toString() {
    return manipulator.toString();
  }
}
