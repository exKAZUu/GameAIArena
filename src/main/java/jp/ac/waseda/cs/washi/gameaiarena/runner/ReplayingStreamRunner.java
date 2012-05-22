package jp.ac.waseda.cs.washi.gameaiarena.runner;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class ReplayingStreamRunner<Arg, Result extends Serializable, Controller>
    extends AbstractRunner<Arg, Result, Controller> {

  private final AbstractRunner<Arg, Result, Controller> controller;
  private final ObjectInputStream ois;

  public ReplayingStreamRunner(AbstractRunner<Arg, Result, Controller> controller,
      ObjectInputStream ois) {
    this.controller = controller;
    this.ois = ois;
  }

  @Override
  public Controller getController() {
    return controller.getController();
  }

  @Override
  public void runPreProcessing(Arg input) {
    controller.runPreProcessing(input);
  }

  @Override
  public void runProcessing() {
    controller.runProcessing();
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
    return controller.toString();
  }
}
