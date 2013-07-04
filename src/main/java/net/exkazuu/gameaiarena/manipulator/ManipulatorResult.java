package net.exkazuu.gameaiarena.manipulator;

import java.io.Serializable;

public class ManipulatorResult<Result extends Serializable> {
  private Result result = null;
  private boolean finished = false;

  void setResult(Result result) {
    this.result = result;
    this.finished = true;
  }

  public Result getResult() {
    if (!isFinished()) {
      throw new IllegalStateException("The result is not acquired yet.");
    }
    return result;
  }

  public boolean isFinished() {
    return finished;
  }
}
