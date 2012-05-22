package jp.ac.waseda.cs.washi.gameaiarena.gui;

import jp.ac.waseda.cs.washi.gameaiarena.common.Environment;

public abstract class Scene<Env extends Environment> {

  private Env environment;

  public Env getEnvironment() {
    return environment;
  }

  final void setEnvironment(Env environment) {
    this.environment = environment;
  }

  public void initialize() {}

  public void release() {}

  public Scene<Env> run() {
    return this;
  }

  public void draw() {}
}
