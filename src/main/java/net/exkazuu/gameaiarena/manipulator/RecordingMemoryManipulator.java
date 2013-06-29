package net.exkazuu.gameaiarena.manipulator;

import java.io.Serializable;
import java.util.List;

public class RecordingMemoryManipulator<Arg, Result extends Serializable, Controller>
    extends Manipulator<Arg, Result, Controller> {

  private final Manipulator<Arg, Result, Controller> manipulator;
  private final List<Result> oos;

  public RecordingMemoryManipulator(Manipulator<Arg, Result, Controller> manipulator,
      List<Result> oos) {
    this.manipulator = manipulator;
    this.oos = oos;
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
