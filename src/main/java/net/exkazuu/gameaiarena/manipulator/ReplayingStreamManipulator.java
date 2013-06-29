package net.exkazuu.gameaiarena.manipulator;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class ReplayingStreamManipulator<Arg, Result extends Serializable, Controller>
    extends Manipulator<Arg, Result, Controller> {

  private final Manipulator<Arg, Result, Controller> manipulator;
  private final ObjectInputStream ois;

  public ReplayingStreamManipulator(Manipulator<Arg, Result, Controller> manipulator,
      ObjectInputStream ois) {
    this.manipulator = manipulator;
    this.ois = ois;
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

  @SuppressWarnings("unchecked")
  @Override
  public Result runPostProcessing() {
    try {
      return (Result) ois.readObject();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public String toString() {
    return manipulator.toString();
  }
}
