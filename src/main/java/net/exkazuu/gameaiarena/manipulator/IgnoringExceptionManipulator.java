package net.exkazuu.gameaiarena.manipulator;

import java.io.Serializable;

public class IgnoringExceptionManipulator<Arg, Result extends Serializable>
    extends DefaultManipulator<Arg, Result> {

  public IgnoringExceptionManipulator(Manipulator<Arg, Result> manipulator) {
    super(manipulator);
  }

  @Override
  protected void runPreProcessing(Arg input) {
    try {
      manipulator.runPreProcessing(input);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  protected void sendDataToAI(Arg input) {
    try {
      manipulator.sendDataToAI(input);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  protected void receiveDataFromAI(Arg input) {
    try {
      manipulator.receiveDataFromAI(input);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  protected Result runPostProcessing(Arg input) {
    try {
      return manipulator.runPostProcessing(input);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
