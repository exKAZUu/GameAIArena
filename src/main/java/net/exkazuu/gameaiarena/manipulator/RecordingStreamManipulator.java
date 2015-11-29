package net.exkazuu.gameaiarena.manipulator;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class RecordingStreamManipulator<Arg, Result extends Serializable>
    extends DefaultManipulator<Arg, Result> {

  private final ObjectOutputStream oos;

  public RecordingStreamManipulator(Manipulator<Arg, Result> manipulator, ObjectOutputStream oos) {
    super(manipulator);
    this.oos = oos;
  }

  @Override
  protected Result runPostProcessing(Arg input) {
    Result act = manipulator.runPostProcessing(input);
    try {
      oos.writeObject(act);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return act;
  }
}
