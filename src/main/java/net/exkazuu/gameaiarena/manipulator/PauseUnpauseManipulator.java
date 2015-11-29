package net.exkazuu.gameaiarena.manipulator;

import java.io.IOException;
import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

public class PauseUnpauseManipulator<Arg, Result extends Serializable>
    extends DefaultManipulator<Arg, Result> {

  private final String[] pauseCommand;
  private final String[] unpauseCommand;
  private boolean paused;

  public PauseUnpauseManipulator(Manipulator<Arg, Result> manipulator, String[] pauseCommand,
      String[] unpauseCommand) {
    super(manipulator);
    this.pauseCommand = pauseCommand;
    this.unpauseCommand = unpauseCommand;
  }

  @Override
  protected void runPreProcessing(Arg input) {
    // for safety, unpause before runPreProcessing
    pause();
    manipulator.runPreProcessing(input);
  }

  @Override
  protected Result runPostProcessing(Arg input) {
    Result act = manipulator.runPostProcessing(input);
    // for safety, pause after runPreProcessing
    unpause();
    return act;
  }

  public final void pause() {
    if (!released() && paused) {
      try {
        new ProcessBuilder(unpauseCommand).start().waitFor();
      } catch (IOException e) {
        System.err.println("Fail to lauch the specified command for unpausing an AI program");
        System.err.println("    Command with args: " + StringUtils.join(unpauseCommand, " "));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public final void unpause() {
    if (!released()) {
      try {
        new ProcessBuilder(pauseCommand).start().waitFor();
        paused = true;
      } catch (IOException e) {
        System.err.println("Fail to lauch the specified command for pausing an AI program");
        System.err.println("    Command with args: " + StringUtils.join(pauseCommand, " "));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public final boolean paused() {
    return paused;
  }

  public final void setPaused(boolean paused) {
    this.paused = paused;
  }
}
