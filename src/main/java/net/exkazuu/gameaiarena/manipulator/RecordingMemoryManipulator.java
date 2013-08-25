package net.exkazuu.gameaiarena.manipulator;

import java.io.Serializable;
import java.util.List;

public class RecordingMemoryManipulator<Arg, Result extends Serializable>
    extends Manipulator<Arg, Result> {

  private final Manipulator<Arg, Result> manipulator;
  private final List<Result> oos;

  public RecordingMemoryManipulator(Manipulator<Arg, Result> manipulator,
      List<Result> oos) {
    this.manipulator = manipulator;
    this.oos = oos;
  }

  @Override
  public void runPreProcessing(Arg input) {
    manipulator.runPreProcessing(input);
  }

  @Override
  public void runProcessing() {
    manipulator.runProcessing();
  }

  @Override
  public Result runPostProcessing() {
    Result act = manipulator.runPostProcessing();
    oos.add(act);
    return act;
  }

  @Override
  public String toString() {
    return manipulator.toString();
  }
}
