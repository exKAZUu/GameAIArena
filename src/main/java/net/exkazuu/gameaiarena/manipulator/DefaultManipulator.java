package net.exkazuu.gameaiarena.manipulator;

import java.io.Serializable;

public class DefaultManipulator<Arg, Result extends Serializable> extends Manipulator<Arg, Result> {

  protected final Manipulator<Arg, Result> manipulator;

  public DefaultManipulator(Manipulator<Arg, Result> manipulator) {
    this.manipulator = manipulator;
  }

  @Override
  protected void runPreProcessing(Arg input) {
    manipulator.runPreProcessing(input);
  }

  @Override
  protected void sendDataToAI(Arg input) {
    manipulator.sendDataToAI(input);
  }

  @Override
  protected void receiveDataFromAI() {
    manipulator.receiveDataFromAI();
  }

  @Override
  protected Result runPostProcessing() {
    return manipulator.runPostProcessing();
  }

  @Override
  public String toString() {
    return manipulator.toString();
  }
}
