package net.exkazuu.gameaiarena.manipulator;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class ReplayingStreamManipulator<Arg, Result extends Serializable>
    extends DefaultManipulator<Arg, Result> {

  private final ObjectInputStream ois;

  public ReplayingStreamManipulator(Manipulator<Arg, Result> manipulator, ObjectInputStream ois) {
    super(manipulator);
    this.ois = ois;
  }

  @Override
  protected void sendDataToAI(Arg input) {}

  @Override
  protected void receiveDataFromAI(Arg input) {}

  @SuppressWarnings("unchecked")
  @Override
  public Result runPostProcessing(Arg input) {
    try {
      return (Result) ois.readObject();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }
}
