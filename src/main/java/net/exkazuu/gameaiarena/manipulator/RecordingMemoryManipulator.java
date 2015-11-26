package net.exkazuu.gameaiarena.manipulator;

import java.io.Serializable;
import java.util.List;

public class RecordingMemoryManipulator<Arg, Result extends Serializable>
    extends DefaultManipulator<Arg, Result> {
  private final List<Result> oos;

  public RecordingMemoryManipulator(Manipulator<Arg, Result> manipulator, List<Result> oos) {
    super(manipulator);
    this.oos = oos;
  }

  @Override
  protected Result runPostProcessing() {
    Result act = manipulator.runPostProcessing();
    oos.add(act);
    return act;
  }
}
