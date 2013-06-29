package net.exkazuu.gameaiarena.manipulator;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class RecordingStreamManipulator<Arg, Result extends Serializable, Controller>
    extends Manipulator<Arg, Result, Controller> {

  private final Manipulator<Arg, Result, Controller> manipulator;
  private final ObjectOutputStream oos;

  public RecordingStreamManipulator(Manipulator<Arg, Result, Controller> manipulator,
      ObjectOutputStream oos) {
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
    try {
      oos.writeObject(act);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return act;
  }

  @Override
  public String toString() {
    return manipulator.toString();
  }
}
