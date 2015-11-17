package net.exkazuu.gameaiarena.manipulator;

import java.io.IOException;
import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

public class PauseUnpauseManipulator<Arg, Result extends Serializable>
    extends Manipulator<Arg, Result> {

  private final Manipulator<Arg, Result> manipulator;
  private final String[] pauseCommand;
  private final String[] unpauseCommand;

  public PauseUnpauseManipulator(Manipulator<Arg, Result> manipulator, String[] pauseCommand,
      String[] unpauseCommand) {
    this.manipulator = manipulator;
    this.pauseCommand = pauseCommand;
    this.unpauseCommand = unpauseCommand;
  }

  @Override
  public void runPreProcessing(Arg input) {
    // for safety, unpause before runPreProcessing
    try {
      new ProcessBuilder(unpauseCommand).start();
    } catch (IOException e) {
      System.err.println("Fail to lauch the specified command for unpausing an AI program");
      System.err.println("    Command with args: " + StringUtils.join(unpauseCommand, " "));
    }
    manipulator.runPreProcessing(input);
  }

  @Override
  public void runProcessing() {
    manipulator.runProcessing();
  }

  @Override
  public Result runPostProcessing() {
    Result act = manipulator.runPostProcessing();
    // for safety, pause after runPreProcessing
    try {
      new ProcessBuilder(pauseCommand).start();
    } catch (IOException e) {
      System.err.println("Fail to lauch the specified command for pausing an AI program");
      System.err.println("    Command with args: " + StringUtils.join(pauseCommand, " "));
    }
    return act;
  }

  @Override
  public String toString() {
    return manipulator.toString();
  }
}
