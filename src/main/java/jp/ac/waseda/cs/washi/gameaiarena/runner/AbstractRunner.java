package jp.ac.waseda.cs.washi.gameaiarena.runner;

import java.io.Serializable;

public abstract class AbstractRunner<Arg, Result extends Serializable, Controller> {

  /**
   * Gets an actual an instance of an AI class.
   * 
   * @return an actual an instance of an AI class
   */
  public abstract Controller getController();

  public final Result run(Arg input) {
    runPreProcessing(input);
    runProcessing();
    return runPostProcessing();
  }

  protected abstract void runPreProcessing(Arg input);

  protected abstract void runProcessing();

  protected abstract Result runPostProcessing();
}
