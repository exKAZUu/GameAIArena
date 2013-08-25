package net.exkazuu.gameaiarena.manipulator;

import java.io.Serializable;

public class IgnoringExceptionManipulator<Arg, Result extends Serializable>
    extends Manipulator<Arg, Result> {

  private final Manipulator<Arg, Result> manipulator;

  public IgnoringExceptionManipulator(Manipulator<Arg, Result> manipulator) {
    this.manipulator = manipulator;
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
